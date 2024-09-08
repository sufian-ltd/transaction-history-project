package com.example.transactionhistorybackend.batch.processor;

import com.example.transactionhistorybackend.model.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TransactionItemProcessor implements ItemProcessor<Transaction, Transaction> {

    @Override
    public Transaction process(Transaction transaction) {
        return transaction;
    }
}