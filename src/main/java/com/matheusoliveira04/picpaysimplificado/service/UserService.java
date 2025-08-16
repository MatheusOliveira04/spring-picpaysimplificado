package com.matheusoliveira04.picpaysimplificado.service;

import com.matheusoliveira04.picpaysimplificado.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User findById(@NotNull UUID id);

    List<User> findAll();

    User insert(@Valid User user);

    User update(@Valid User user);

    void delete(UUID id);
}
