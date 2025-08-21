package com.matheusoliveira04.picpaysimplificado.model;

import com.matheusoliveira04.picpaysimplificado.model.enums.UserType;
import com.matheusoliveira04.picpaysimplificado.validation.annotation.CpfCnpjFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Entity(name = "users")
public class User {
    @Id
    @Setter
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
    @OneToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", nullable = false, unique = true)
    private Wallet wallet;

}
