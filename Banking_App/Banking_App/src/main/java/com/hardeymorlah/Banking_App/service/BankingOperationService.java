package com.hardeymorlah.Banking_App.service;

import com.hardeymorlah.Banking_App.exception.TransactionException;
import com.hardeymorlah.Banking_App.model.AccountUser;
import com.hardeymorlah.Banking_App.model.BankAccount;
import com.hardeymorlah.Banking_App.model.DTOs.TransactionType;
import com.hardeymorlah.Banking_App.model.Transactions;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class BankingOperationService {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountUserService accountUserService;

    public ResponseEntity<BankAccount> depositFund(String accountNumber, double amount, String transId) throws MessagingException {
        if( amount <= 0 ){
            System.out.println(amount);
            throw new TransactionException("You can not deposit negative amount");
        }
        BankAccount account = bankAccountService.getByAccountNumber(accountNumber).getBody();
        assert account != null;

        account.setAccountBalance(amount + account.getAccountBalance());

        Transactions transaction = new Transactions();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionId(transId);
        transactionService.postNewTransaction(transaction);
        AccountUser user = accountUserService.getAccountUserById(account.getUser().getId()).getBody();
        assert user != null;
        messageService.depositNotification(user.getFirstName(), user.getUsername(), amount);
        return new ResponseEntity<>(bankAccountService.updateAccount(account).getBody(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> withdrawFund(String accountNumber, double amount, String transId) throws MessagingException{
        if( amount <= 0 ){
            System.out.println(amount);
            throw new TransactionException("You can not withdraw negative amount");
        }
        BankAccount account = bankAccountService.getByAccountNumber(accountNumber).getBody();
        assert account != null;

        if( account.getAccountBalance() < amount ){
            throw new TransactionException("Insufficient Balance");
        }
        account.setAccountBalance(account.getAccountBalance() - amount);

        Transactions transaction = new Transactions();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setTransactionId(transId);
        transactionService.postNewTransaction(transaction);
        AccountUser user = accountUserService.getAccountUserById(account.getUser().getId()).getBody();
        assert user != null;
        messageService.withdrawalNotification(user.getFirstName(), user.getUsername(), amount);
        return new ResponseEntity<>(bankAccountService.updateAccount(account).getBody(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> transferFunds(String accountFrom, String accountTo, double amount ) throws MessagingException{
        try{
            String transId = transactionService.generateTxnId();
            withdrawFund(accountFrom, amount, transId);
            depositFund(accountTo, amount, transId);
            return new ResponseEntity<>("Transaction Successful", HttpStatus.OK);
        } catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
        }
        return new ResponseEntity<>("Transaction failed", HttpStatus.NOT_ACCEPTABLE);
    }
}
