package com.cristianogregio.wex.transactionstore.repository;

import com.cristianogregio.wex.transactionstore.model.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, UUID> {
}
