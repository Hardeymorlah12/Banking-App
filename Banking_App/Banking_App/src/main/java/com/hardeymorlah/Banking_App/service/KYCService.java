package com.hardeymorlah.Banking_App.service;
import com.hardeymorlah.Banking_App.model.KnowYourCustomer;
import com.hardeymorlah.Banking_App.repository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KYCService {

    @Autowired
    private KYCRepository kycRepository;

    public ResponseEntity<List<KnowYourCustomer>> getAllKYC(){
        return new ResponseEntity<>(kycRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<KnowYourCustomer> getById(int id){
        return new ResponseEntity<>(kycRepository.findById(id).get(), HttpStatus.OK);
    }


    public ResponseEntity<KnowYourCustomer> createCustomerKYC( KnowYourCustomer customer ){
        return new ResponseEntity<>(kycRepository.save(customer), HttpStatus.CREATED);
    }

    public ResponseEntity<KnowYourCustomer> updateCustomerKYC(KnowYourCustomer customer, int id){
        KnowYourCustomer kycCustomer = kycRepository.findById(id).get();

        kycCustomer.setAddress(customer.getAddress());
        kycCustomer.setDateOfBirth(customer.getDateOfBirth());
        kycCustomer.setNin(customer.getNin());
        kycCustomer.setBankVerificationNumber(customer.getBankVerificationNumber());
        kycCustomer.setNextOfKin(customer.getNextOfKin());
        kycCustomer.setStateOfResidence(customer.getStateOfResidence());

        return new ResponseEntity<>(kycRepository.save(customer), HttpStatus.CREATED);
    }


}
