package com.example.transactionhistorybackend.controller;

import com.example.transactionhistorybackend.constants.ApiUrlConstant;
import com.example.transactionhistorybackend.model.Transaction;
import com.example.transactionhistorybackend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.TRANSACTIONS)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Page<Transaction>> getTransactions(@RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Page<Transaction> transactions = transactionService.searchTransactions(keyword, PageRequest.of(page, size));
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestParam String description) {
        try {
            Transaction updatedTransaction = transactionService.updateTransaction(id, description);
            return ResponseEntity.ok(updatedTransaction);
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}