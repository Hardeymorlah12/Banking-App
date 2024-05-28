package com.hardeymorlah.Banking_App.service;

import com.hardeymorlah.Banking_App.model.AccountUser;
import com.hardeymorlah.Banking_App.model.BankAccount;
import com.hardeymorlah.Banking_App.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public ResponseEntity<List<BankAccount>> getAllBankAccounts(){
        return new ResponseEntity<>(bankAccountRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> getById(int id){
        return new ResponseEntity<>(bankAccountRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> getByUser(AccountUser user){
        return new ResponseEntity<>(bankAccountRepository.findByUser(user), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> getByAccountNumber(String accountNumber){
        return new ResponseEntity<>(bankAccountRepository.findBankAccountByAccountNumber(accountNumber), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> updateAccount(BankAccount bankAccount){
        return new ResponseEntity<>(bankAccountRepository.save(bankAccount), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> createBankAccount(AccountUser accountUser, double openingAmount ){
        StringBuilder accountNumber = new StringBuilder();
        int count = 0;
        while(count < 10){
            int randomInt = new Random().nextInt(10);
            accountNumber.append(randomInt);
            count++;
        }
        BankAccount account = new BankAccount(accountNumber.toString(), openingAmount, accountUser);
        return new ResponseEntity<>(bankAccountRepository.save(account), HttpStatus.CREATED);
    }

    public ResponseEntity<BankAccount> createBankAccount(AccountUser accountUser ){
        StringBuilder accountNumber = new StringBuilder();
        int count = 0;
        while(count < 10){
            int randomInt = new Random().nextInt(10);
            accountNumber.append(randomInt);
            count++;
        }
        BankAccount account = new BankAccount(accountNumber.toString(), 0.00, accountUser);
        return new ResponseEntity<>(bankAccountRepository.save(account), HttpStatus.CREATED);
    }

}
