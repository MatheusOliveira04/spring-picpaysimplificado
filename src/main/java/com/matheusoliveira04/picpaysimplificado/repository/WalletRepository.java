package com.matheusoliveira04.picpaysimplificado.repository;

import com.matheusoliveira04.picpaysimplificado.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
