package com.matheusoliveira04.picpaysimplificado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "wallets")
@Entity(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @PositiveOrZero
    @Column(nullable = false, scale = 2)
    private BigDecimal balance;
    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    private User user;
}
