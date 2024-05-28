package com.hardeymorlah.Banking_App.CustomAnnotaion;

//import com.Hardeymorlah.BankingApplicationDevelopment.Model.EmailOrUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailOrUsernameValidator.class)
public @interface EmailOrUsername {
    String message() default "input must be either a valid email or username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
