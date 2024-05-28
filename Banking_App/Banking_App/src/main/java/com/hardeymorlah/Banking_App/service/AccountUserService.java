package com.hardeymorlah.Banking_App.service;


import com.hardeymorlah.Banking_App.config.AccountConfiguration;
import com.hardeymorlah.Banking_App.model.AccountUser;
import com.hardeymorlah.Banking_App.model.DTOs.LoginRequest;
import com.hardeymorlah.Banking_App.model.DTOs.LoginResponse;
import com.hardeymorlah.Banking_App.model.DTOs.Role;
import com.hardeymorlah.Banking_App.repository.AccountUserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class AccountUserService {

    @Autowired
    private AccountUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountConfiguration accountConfiguration;

    @Autowired
    private BankAccountService bankAccountService;

    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id){
        return new ResponseEntity<>(userRepository.findById(id).orElseThrow(), HttpStatus.OK);
    }

    public ResponseEntity<LoginResponse> authenticate(LoginRequest request) throws MessagingException {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


            if( auth != null ){
                AccountUser user = userRepository.getByUsername(request.getUsername());
                System.out.println(user);
                String token = jwtService.createToken(user);
                System.out.println(token);
                messageService.loginNotification(user.getUsername(), STR."Dear \{user.getFirstName()}\nThere has been a successful login into your Banking Account. Please if you did not log in call us on the following number: 01-2245845, 08004455454\nThank you for Banking with Us.");
                return new ResponseEntity<>(LoginResponse.builder().user(user).token(token).build(), HttpStatus.OK);
            } else {
                System.out.println("auth is null");
            }

        return null;
    }

    public ResponseEntity<AccountUser> getAccountUserByUsername(@RequestParam String username){
        return new ResponseEntity<>(userRepository.getByUsername(username), HttpStatus.OK);
    }


    public ResponseEntity<AccountUser> postAccountUser(@RequestBody AccountUser user) throws MessagingException {
        passwordEncoder = accountConfiguration.passwordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
       messageService.registrationNotification(user.getUsername(), user.getFirstName());
        AccountUser savedUser = userRepository.save(user);
        bankAccountService.createBankAccount(savedUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, @RequestBody AccountUser user){
        AccountUser updatedUser = userRepository.findById(id).orElseThrow();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setMiddleName(user.getMiddleName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
    }


    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id){
        AccountUser deletedUser = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
