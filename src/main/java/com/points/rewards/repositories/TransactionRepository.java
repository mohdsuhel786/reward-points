package com.points.rewards.repositories;

import com.points.rewards.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByTransactionDateBetween(LocalDate start, LocalDate end);
}
