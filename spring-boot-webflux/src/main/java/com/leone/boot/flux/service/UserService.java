package com.leone.boot.flux.service;

import com.leone.boot.common.entity.User;
import com.leone.boot.common.util.EntityFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

//    @Autowired
//    private UserRepository userRepository;

    private final Map<String, User> data = new ConcurrentHashMap<>();

    private final List<User> users = EntityFactory.getUsers(20);


    public Mono<User> getUserById(String id) {
        return Mono.justOrEmpty(users.stream().filter(user ->
                user.getUserId().equals(id)
        ).findFirst().orElse(null));
    }

    public Flux<User> getUserByIds(Flux<String> ids) {

//        return ids.flatMap(id -> {
//            users.stream().filter(user -> user.getId().equals(ids)).findFirst().orElse(null);
//        });
        return null;
    }


    public Flux<User> getUsers() {
        return Flux.fromIterable(this.users);
    }

    Flux<User> getById(final Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    Mono<User> getById(final String id) {
        return Mono.justOrEmpty(this.data.get(id)).switchIfEmpty(Mono.error(new /*ResourceNotFound*/Exception()));
    }

    Flux<User> createOrUpdate(final Flux<User> users) {
        return users.doOnNext(user -> this.data.put(user.getUserId().toString(), user));
    }

    Mono<User> createOrUpdate(final User user) {
        this.data.put(user.getUserId().toString(), user);
        return Mono.just(user);
    }

    Mono<User> delete(final String id) {
        return Mono.justOrEmpty(this.data.remove(id));
    }


    /**
     * 保存或更新。
     * 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错，
     * 这时找到以保存的user记录用传入的user更新它。
     */
//    public Mono<User> save(User user) {
//        return userRepository.save(user).onErrorResume(e ->
//                        userRepository.findByUsername(user.getUsername())
//                                .flatMap(originalUser -> {
//                                    user.setId(originalUser.getId());
//                                    return userRepository.save(user);
//                                }));
//    }
//
//    public Mono<Long> deleteByUsername(String username) {
//        return userRepository.deleteByUsername(username);
//    }
//
//    public Mono<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    public Flux<User> findAll() {
//        return userRepository.findAll();
//    }


}
