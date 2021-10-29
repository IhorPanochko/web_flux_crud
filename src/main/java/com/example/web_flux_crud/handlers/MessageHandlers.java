package com.example.web_flux_crud.handlers;

import com.example.web_flux_crud.documents.Messages;
import com.example.web_flux_crud.service.interfaces.MessageService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Component
public class MessageHandlers {

    private final MessageService messageService;

    public MessageHandlers(MessageService messageService) {
        this.messageService = messageService;
    }

    public Mono<ServerResponse> createMessage(ServerRequest request) {
        return request.bodyToMono(Messages.class)
                .flatMap(messageService::createMessage)
                .flatMap(messages -> ServerResponse.ok().body(BodyInserters.fromValue(messages)));
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Messages> people = messageService.findAllMessages();
        return ok().body(people, Messages.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Messages> messagesMono = messageService.findById(id);
        return ok().body(messagesMono, Messages.class);

    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Messages.class)
                .flatMap(messageService::updateMessage)
                .flatMap(messages -> ServerResponse.ok().body(BodyInserters.fromValue(messages)));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Void> voidMono = messageService.deleteById(id);
        return ok().body(voidMono, Messages.class);

//        return request.bodyToMono(Messages.class)
//                .flatMap(messages -> messageService.deleteById(id))
//                .flatMap(messages -> ServerResponse.ok().body(BodyInserters.fromValue(messages)));
    }
}
