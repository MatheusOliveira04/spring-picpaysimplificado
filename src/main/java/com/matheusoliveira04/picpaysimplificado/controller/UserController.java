package com.matheusoliveira04.picpaysimplificado.controller;

import com.matheusoliveira04.picpaysimplificado.dto.request.UserRequest;
import com.matheusoliveira04.picpaysimplificado.dto.response.UserResponse;
import com.matheusoliveira04.picpaysimplificado.mapper.UserMapper;
import com.matheusoliveira04.picpaysimplificado.model.User;
import com.matheusoliveira04.picpaysimplificado.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/v1/users")
@RestController
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable String id) {
        User user = service.findById(UUID.fromString(id));
        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok(mapper.toResponse(users));
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriBuilder) {
        User user = mapper.toModel(userRequest);
        User savedUser = service.insert(user);
        return ResponseEntity
                .created(uriBuilder.path("/v1/users/{id}").buildAndExpand(savedUser.getId()).toUri())
                .body(mapper.toResponse(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable String id, @RequestBody UserRequest userRequest) {
        User user = mapper.toModel(userRequest);
        user.setId(UUID.fromString(id));
        var updatedUser = service.update(user);
        return ResponseEntity.ok(mapper.toResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
