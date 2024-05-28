package com.hardeymorlah.Banking_App.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity
@Table(name = "kyc")
public class KnowYourCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String bankVerificationNumber;
    @Setter
    @Getter
    private String nin;
    @Getter
    @Setter
    private String localGovtOfResidence;
    @Getter
    @Setter
    private String stateOfResidence;
    @Getter
    @Setter
    private String dateOfBirth;

    @Getter
    @Setter
    private String nextOfKin;

//    public KnowYourCustomer(){}
    public KnowYourCustomer(String address, String bankVerificationNumber, String nin, String localGovtOfResidence, String stateOfResidence, String dateOfBirth, String nextOfKin, AccountUser user) {
        this.address = address;
        this.bankVerificationNumber = bankVerificationNumber;
        this.nin = nin;
        this.localGovtOfResidence = localGovtOfResidence;
        this.stateOfResidence = stateOfResidence;
        this.dateOfBirth = dateOfBirth;
        this.nextOfKin = nextOfKin;
        this.user = user;
    }

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private AccountUser user;


}
