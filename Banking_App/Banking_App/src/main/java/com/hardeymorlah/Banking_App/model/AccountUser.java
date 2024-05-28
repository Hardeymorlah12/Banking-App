package com.hardeymorlah.Banking_App.model;

import com.hardeymorlah.Banking_App.CustomAnnotaion.EmailOrUsername;
import com.hardeymorlah.Banking_App.model.DTOs.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*
Beans to Create
userDetailsService
passWordEncoder;
authenticationManager;
authenticationProvider;
 */

@Entity(name = "users_table")
public class AccountUser implements UserDetails {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Getter
    @Setter
    @NotBlank
    @Length(min = 3)
    private String firstName;
    @Getter
    @Setter
    private String middleName;
    @Getter
    @Setter
    @NotBlank(message = "Last Name cannot be blank")
    @Length(min = 3, max = 25, message = "Last Name can't be less than 3 characters")
//    @Length(min = 3)
    private String lastName;

    @Setter
    @NotBlank
    @Column(unique = true)
    @EmailOrUsername
    private String username;

    @Setter
    @Getter
    @NotBlank
//    @Length(min = 6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%+=^*?&]).{8,}$")
    private String password;

    @Setter
    @Getter
    @NotBlank(message = "Phone Number cannot be blank")
    @Pattern(regexp = "[0-9]{11}", message = "Phone number must be numbers between 0 and 9 and must be 11 digits")
    private String phoneNumber;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    public AccountUser(){}

    public AccountUser(String firstName, String middleName, String lastName, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name())); //List<SimpleGrantedAuthority>
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return STR."AccountUser{firstName='\{firstName}\{'\''}, middleName='\{middleName}\{'\''}, lastName='\{lastName}\{'\''}, username='\{username}\{'\''}, password='\{password}\{'\''}, phoneNumber='\{phoneNumber}\{'\''}\{'}'}";
    }
}
