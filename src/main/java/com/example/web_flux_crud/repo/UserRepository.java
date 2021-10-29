package com.example.web_flux_crud.repo;

import com.example.web_flux_crud.documents.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<Users, String> {

    Mono<Users> findByUsername(String userName);

}
