package com.example.reactiveweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveWebApplication {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingHandler::hello);
    }

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebApplication.class, args);
	}

}

@Component
class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        String name = request.queryParam("name").get();

        String reversed = reverse(name);
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject(
                        "Hello, " + reversed + "!"));
    }

    public String reverse(String text) {
        return text.chars()
                .mapToObj(c -> (char)c)
                .reduce("", (s,c) -> c+s, (s1,s2) -> s2+s1);
    }
}
