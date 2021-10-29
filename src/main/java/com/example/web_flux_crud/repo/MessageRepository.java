package com.example.web_flux_crud.repo;

import com.example.web_flux_crud.documents.Messages;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Messages, String> {
}
