package com.matheusoliveira04.picpaysimplificado.controller;

import com.matheusoliveira04.picpaysimplificado.dto.request.UserRequest;
import com.matheusoliveira04.picpaysimplificado.dto.response.UserResponse;
import com.matheusoliveira04.picpaysimplificado.model.User;
import com.matheusoliveira04.picpaysimplificado.model.enums.UserType;
import com.matheusoliveira04.picpaysimplificado.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/v1/users")
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable String id) {
        User user = service.findById(UUID.fromString(id));
        return ResponseEntity.ok(
            new UserResponse(
                user.getId().toString(),
                user.getName(),
                user.getCpfCnpj(),
                user.getEmail(),
                user.getType().name()
            )
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = service.findAll();
        List<UserResponse> userResponses = users.stream()
            .map(user -> new UserResponse(
                user.getId().toString(),
                user.getName(),
                user.getCpfCnpj(),
                user.getEmail(),
                user.getType().name()
            ))
            .toList();
        return ResponseEntity.ok(userResponses);
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@RequestBody @Valid UserRequest userRequest) {
        User user = new User(
            null,
            userRequest.name(),
            userRequest.cpfCnpj(),
            userRequest.email(),
            userRequest.password(),
            UserType.fromDescription(userRequest.type())
        );
        User savedUser = service.insert(user);
        return ResponseEntity.ok(
            new UserResponse(
                savedUser.getId().toString(),
                savedUser.getName(),
                savedUser.getCpfCnpj(),
                savedUser.getEmail(),
                savedUser.getType().name()
            )
        );
    }

}
