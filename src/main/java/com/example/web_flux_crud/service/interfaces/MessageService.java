package com.example.web_flux_crud.service.interfaces;

import com.example.web_flux_crud.documents.Messages;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<Messages> createMessage(Messages messages);

    Mono<Messages> findById(String id);

    Flux<Messages> findAllMessages();

    Mono<Messages> updateMessage(Messages employee);

    Mono<Void> deleteById(String id);
}
