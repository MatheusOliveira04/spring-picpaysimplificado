package com.matheusoliveira04.picpaysimplificado.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfers")
@Entity(name = "transfers")
public class Transfer {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, scale = 2)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "payer_user_id", nullable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_user_id", nullable = false)
    private User payee;
}
