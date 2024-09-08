package com.example.transactionhistorybackend.repository;

import com.example.transactionhistorybackend.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.customerId LIKE %:keyword% OR t.accountNumber LIKE %:keyword% OR t.description LIKE %:keyword%")
    Page<Transaction> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
