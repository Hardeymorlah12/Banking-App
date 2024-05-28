package com.hardeymorlah.Banking_App.repository;
import com.hardeymorlah.Banking_App.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByTransactionId(String transactionId);

}
