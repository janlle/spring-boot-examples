package com.andy.flux.handler;

import com.andy.flux.entity.User;
import com.andy.flux.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: Leone
 * @since: 2018-04-03 15:44
 **/
@Component
public class UserHandler {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReactiveRedisConnection connection;

    public Mono<ServerResponse> getTime(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }

    public Mono<ServerResponse> getDate(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
    }

    public Mono<ServerResponse> sendTimePerSec(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.interval(Duration.ofSeconds(1)).map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }


    public Mono<ServerResponse> register(ServerRequest request) {
        Mono<Map> body = request.bodyToMono(Map.class);
        return body.flatMap(map -> {
            String username = (String) map.get("username");
            String password = (String) map.get("password");
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            return connection.stringCommands()
                    .set(ByteBuffer.wrap(username.getBytes()), ByteBuffer.wrap(hashedPassword.getBytes()));
        }).flatMap(aBoolean -> {
            Map<String, String> result = new HashMap<>();
            ServerResponse serverResponse = null;
            if (aBoolean) {
                result.put("message", "successful");
                return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(BodyInserters.fromObject(result));
            } else {
                result.put("message", "failed");
                return ServerResponse.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(BodyInserters.fromObject(request));
            }
        });

    }

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<Map> body = request.bodyToMono(Map.class);
        return body.flatMap(map -> {
            String username = (String) map.get("username");
            String password = (String) map.get("password");
            return connection.stringCommands().get(ByteBuffer.wrap(username.getBytes())).flatMap(byteBuffer -> {
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes, 0, bytes.length);
                String hashedPassword = null;
                try {
                    hashedPassword = new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Map<String, String> result = new HashMap<>();
                if (hashedPassword == null || !BCrypt.checkpw(password, hashedPassword)) {
                    result.put("message", "账号或密码错误");
                    return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .body(BodyInserters.fromObject(result));
                } else {
                    result.put("token", "无效token");
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .body(BodyInserters.fromObject(result));
                }
            });
        });
    }

    // 查找用户
    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userRepository.findAll(), User.class);
    }

    // 创建用户
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userRepository.saveAll(user), User.class);
    }


    // 删除用户
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");

        return this.userRepository.findById(id).flatMap(user ->
                userRepository.delete(user).then(ServerResponse.ok().build())
                        .switchIfEmpty(ServerResponse.notFound().build()));

    }


}
