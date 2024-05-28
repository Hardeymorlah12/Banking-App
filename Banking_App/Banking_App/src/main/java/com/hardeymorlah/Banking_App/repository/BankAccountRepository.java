package com.hardeymorlah.Banking_App.repository;

import com.hardeymorlah.Banking_App.model.AccountUser;
import com.hardeymorlah.Banking_App.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByUser(AccountUser user);
    BankAccount findBankAccountByAccountNumber(String accountNumber);
}
