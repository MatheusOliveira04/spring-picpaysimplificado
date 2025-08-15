package com.matheusoliveira04.picpaysimplificado.model;

import com.matheusoliveira04.picpaysimplificado.model.enums.UserType;
import com.matheusoliveira04.picpaysimplificado.validation.annotation.CpfCnpjFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.Objects;
import java.util.UUID;

@Table(name = "users")
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @CpfCnpjFormat
    @Column(nullable = false, unique = true, length = 14)
    private String cpfCnpj;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 8)
    private String password;
    @Column(nullable = false)
    private UserType type;

    public User() {
    }

    public User(UUID id, String name, String cpfCnpj, String email, String password, UserType type) {
        this.id = id;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String cpfCnpj;
        private String email;
        private String password;
        private UserType type;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder cpfCnpj(String cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder type(UserType type) {
            this.type = type;
            return this;
        }

        public User build() {
            return new User(id, name, cpfCnpj, email, password, type);
        }
    }
}
