package com.example.transactionhistorybackend.batch.writer;

import com.example.transactionhistorybackend.model.Transaction;
import com.example.transactionhistorybackend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionItemWriter implements ItemWriter<Transaction> {

    private final TransactionRepository transactionRepository;

    @Override
    public void write(Chunk<? extends Transaction> transactions) {
        transactionRepository.saveAll(transactions.getItems());
    }
}