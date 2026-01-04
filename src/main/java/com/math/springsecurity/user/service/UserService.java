package com.math.springsecurity.user.service;

import com.math.springsecurity.role.entity.Role;
import com.math.springsecurity.role.repository.RoleRepository;
import com.math.springsecurity.user.dto.request.CreateUserRequest;
import com.math.springsecurity.user.dto.response.UserResponse;
import com.math.springsecurity.user.entity.User;
import com.math.springsecurity.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String BASIC_ROLE_NAME = Role.Values.BASIC.name();

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(CreateUserRequest request){
        if (userRepository.findByUsername(request.username()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Role basicRole = roleRepository.findByName(BASIC_ROLE_NAME)
                .orElseThrow(() -> new IllegalStateException("Basic role not found"));

        User user = new User(
                request.username(),
                passwordEncoder.encode(request.password()),
                Set.of(basicRole)
        );

        userRepository.save(user);
    }

    public List<UserResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private UserResponse toResponse(User user){
        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }

}
