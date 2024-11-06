package com.cristianogregio.wex.transactionstore.controller;

import com.cristianogregio.wex.transactionstore.model.PurchaseTransaction;
import com.cristianogregio.wex.transactionstore.model.TransactionState;
import com.cristianogregio.wex.transactionstore.service.PurchaseTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseTransactionController {

    @Autowired
    private PurchaseTransactionService purchaseTransactionService;

    @PostMapping
    public ResponseEntity<PurchaseTransaction> createPurchase(@RequestBody @Valid PurchaseTransaction purchaseTransaction) {
        if(purchaseTransaction.getState().equals(TransactionState.ACCEPT)){
            PurchaseTransaction createdPurchase = purchaseTransactionService.createPurchase(purchaseTransaction);
            return ResponseEntity.ok(createdPurchase);
        }
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
    }


    @GetMapping("/{transactionId}")
    public ResponseEntity<PurchaseTransaction> getPurchaseById(@PathVariable UUID transactionId) {
        Optional<PurchaseTransaction> purchaseTransaction = purchaseTransactionService.getPurchaseById(transactionId);
        return purchaseTransaction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PurchaseTransaction> getAllPurchases() {
        return purchaseTransactionService.getAllPurchases();
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<PurchaseTransaction> updatePurchase(
            @PathVariable UUID transactionId,
            @RequestBody PurchaseTransaction purchaseTransactionDetails) {
        Optional<PurchaseTransaction> updatedPurchase = purchaseTransactionService.updatePurchase(transactionId, purchaseTransactionDetails);
        return updatedPurchase.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deletePurchase(@PathVariable UUID transactionId) {
        boolean deleted = purchaseTransactionService.deletePurchase(transactionId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
