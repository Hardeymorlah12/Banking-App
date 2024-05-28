package com.hardeymorlah.Banking_App.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class AccountResource extends RepresentationModel<AccountResource> {

    private AccountUser accountUser;

}
