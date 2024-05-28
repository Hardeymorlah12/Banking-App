package com.hardeymorlah.Banking_App.model.DTOs;

import com.hardeymorlah.Banking_App.model.AccountUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private AccountUser user;
    private String token;

}
