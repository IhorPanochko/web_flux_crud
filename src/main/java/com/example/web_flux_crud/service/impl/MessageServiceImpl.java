package com.example.web_flux_crud.service.impl;

import com.example.web_flux_crud.documents.Messages;
import com.example.web_flux_crud.events.CustomSpringEventPublisher;
import com.example.web_flux_crud.repo.MessageRepository;
import com.example.web_flux_crud.service.interfaces.MessageService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

    private final CustomSpringEventPublisher customSpringEventPublisher;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(CustomSpringEventPublisher customSpringEventPublisher, MessageRepository messageRepository) {
        this.customSpringEventPublisher = customSpringEventPublisher;
        this.messageRepository = messageRepository;
    }

    @Override
    public Mono<Messages> createMessage(Messages messages) {
        customSpringEventPublisher.publishCustomEvent(messages.getText());
//        String user = UserContext.getUser();
//        SecurityContext context = SecurityContextHolder.getContext();
        return messageRepository.save(messages);
    }

    @Override
    public Mono<Messages> findById(String id) {
        return messageRepository.findById(id);
    }

    @Override
    public Flux<Messages> findAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Mono<Messages> updateMessage(Messages employee) {
        return messageRepository.save(employee);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return messageRepository.deleteById(id);
    }
}
