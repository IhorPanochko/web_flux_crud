package com.example.web_flux_crud.controllers;

import com.example.web_flux_crud.handlers.MessageHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@RestController
public class MessageController {

    @Bean
    RouterFunction<ServerResponse> findAll(MessageHandlers messageHandlers) {
        return RouterFunctions.route()
                .GET("/all", messageHandlers::getAll)
                .POST("/create", messageHandlers::createMessage)
                .PUT("/update", messageHandlers::update)
                .GET("/{id}", messageHandlers::getById)
                .DELETE("/{id}", messageHandlers::delete)
                .build();
    }

}
