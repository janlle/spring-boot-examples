//package com.andy.webflux.test;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.client.WebClient;
//import com.andy.webflux.entity.User;
//
//import lombok.extern.slf4j.Slf4j;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//public class TestClient {
//
//
//	public static void main(String[] args) {
//		WebClient client = WebClient.create("http://localhost:8080/");
//		Mono<User> result = client.post()// 请求方法,get,post...
//				.uri("persion/getPersion/{id}.json", "123")// 请求相对地址以及参数
//				.accept(MediaType.APPLICATION_JSON).retrieve()// 请求类型
//				.bodyToMono(User.class);// 返回类型
//		User user = result.block();
//		
////		log.info(JSONObject.wrap(user).toString());
//	}
//
//}