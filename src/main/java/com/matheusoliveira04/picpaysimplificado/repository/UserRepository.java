package com.matheusoliveira04.picpaysimplificado.repository;

import com.matheusoliveira04.picpaysimplificado.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
