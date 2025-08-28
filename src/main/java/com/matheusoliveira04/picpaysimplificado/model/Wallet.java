package com.matheusoliveira04.picpaysimplificado.model;

import com.matheusoliveira04.picpaysimplificado.exception.service.IntegrityViolationException;
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
    @Setter
    @PositiveOrZero
    @Column(nullable = false, scale = 2)
    private BigDecimal balance;
    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    private User user;

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IntegrityViolationException("Amount to credit must be positive");
        } else {
            this.balance = this.balance.add(amount);
        }
    }

    public void debit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IntegrityViolationException("Amount to debit must be positive");
        } else if (this.balance.compareTo(amount) < 0) {
            throw new IntegrityViolationException("Insufficient balance to debit the specified amount");
        } else {
            this.balance = this.balance.subtract(amount);
        }
    }

}
