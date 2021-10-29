package com.example.web_flux_crud.service.interfaces;

import com.example.web_flux_crud.documents.Users;
import com.example.web_flux_crud.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> saveUser(UserDto userDto);

    Mono<Users> findByUsername(String username);

}
