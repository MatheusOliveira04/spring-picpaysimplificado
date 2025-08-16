package com.matheusoliveira04.picpaysimplificado.service.impl;

import com.matheusoliveira04.picpaysimplificado.exception.ObjectNotFoundException;
import com.matheusoliveira04.picpaysimplificado.model.User;
import com.matheusoliveira04.picpaysimplificado.repository.UserRepository;
import com.matheusoliveira04.picpaysimplificado.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(@NotNull UUID id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> findAll() {
        List<User> listUsers = repository.findAll();
        if (listUsers.isEmpty()) throw new ObjectNotFoundException("No users found");
        return listUsers;
    }

    @Override
    public User insert(@Valid User user) {
        return repository.save(user);
    }

    @Override
    public User update(@Valid User user) {
        findById(user.getId());
        return repository.save(user);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(findById(id));
    }
}
