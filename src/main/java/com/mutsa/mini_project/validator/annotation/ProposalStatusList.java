package com.mutsa.mini_project.validator.annotation;

import com.mutsa.mini_project.validator.ProposalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProposalValidator.class)
public @interface ProposalStatusList {
    String message() default "옳바르지 않은 Status 형태입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
