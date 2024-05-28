package com.hardeymorlah.Banking_App.CustomAnnotaion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;

public class EmailOrUsernameValidator implements ConstraintValidator<EmailOrUsername, String> {
    @Email
    private final static String EMAIL_REGEX  = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" ;
//
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,20}$";

    @Override
    public void initialize(EmailOrUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
//        Check if the input matches either the email or username format
        return value.matches(EMAIL_REGEX) || value.matches(USERNAME_REGEX);
    }
}
