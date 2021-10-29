package com.example.web_flux_crud.service.impl;

import com.example.web_flux_crud.documents.Users;
import com.example.web_flux_crud.dto.UserDto;
import com.example.web_flux_crud.enums.Role;
import com.example.web_flux_crud.repo.UserRepository;
import com.example.web_flux_crud.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDto> saveUser(UserDto userDto) {
        Users user = new Users();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(Role.ROLE_USER));
        userRepository.save(user).subscribe();
        return Mono.just(userDto);
    }

    @Override
    public Mono<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
