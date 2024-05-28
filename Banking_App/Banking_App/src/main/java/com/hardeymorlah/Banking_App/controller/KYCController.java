package com.hardeymorlah.Banking_App.controller;


import com.hardeymorlah.Banking_App.model.KnowYourCustomer;
import com.hardeymorlah.Banking_App.service.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class KYCController {

    @Autowired
    private KYCService kycService;

    @GetMapping("/kyc")
    public ResponseEntity<List<KnowYourCustomer>> getAllKYC(){
        return kycService.getAllKYC();
    }

    @GetMapping("/kyc/{id}")
    public ResponseEntity<KnowYourCustomer> getById(@PathVariable int id){
        return kycService.getById(id);
    }


    @PostMapping("/kyc")
    public ResponseEntity<KnowYourCustomer> createCustomerKYC(@RequestBody KnowYourCustomer customer ){
        return kycService.createCustomerKYC(customer);
    }


    @PutMapping("/kyc")
    public ResponseEntity<KnowYourCustomer> updateCustomerKYC(@RequestBody KnowYourCustomer customer, @PathVariable int id){
        return kycService.updateCustomerKYC(customer, id);
    }



}
