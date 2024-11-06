package com.cristianogregio.wex.transactionstore.service;

import com.cristianogregio.wex.transactionstore.model.PurchaseTransaction;
import com.cristianogregio.wex.transactionstore.repository.PurchaseTransactionRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseTransactionService {

    @Autowired
    private PurchaseTransactionRepository purchaseTransactionRepository;

    public PurchaseTransaction createPurchase(PurchaseTransaction purchaseTransaction) {
        return purchaseTransactionRepository.save(purchaseTransaction);
    }

    public Optional<PurchaseTransaction> getPurchaseById(UUID transactionId) {
        return purchaseTransactionRepository.findById(transactionId);
    }

    public List<PurchaseTransaction> getAllPurchases() {
        return purchaseTransactionRepository.findAll();
    }

    public Optional<PurchaseTransaction> updatePurchase(UUID transactionId, PurchaseTransaction purchaseTransactionDetails) {
        return purchaseTransactionRepository.findById(transactionId).map(purchaseTransaction -> {
            purchaseTransaction.setDescription(purchaseTransactionDetails.getDescription());
            purchaseTransaction.setTransactionDate(purchaseTransactionDetails.getTransactionDate());
            purchaseTransaction.setAmountUSD(purchaseTransactionDetails.getAmountUSD());
            return purchaseTransactionRepository.save(purchaseTransaction);
        });
    }

    public boolean deletePurchase(UUID transactionId) {
        return purchaseTransactionRepository.findById(transactionId).map(purchaseTransaction -> {
            purchaseTransactionRepository.delete(purchaseTransaction);
            return true;
        }).orElse(false);
    }
}
