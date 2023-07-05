package com.mutsa.mini_project.validator;

import com.mutsa.mini_project.validator.annotation.ProposalStatusList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class ProposalValidator implements ConstraintValidator<ProposalStatusList, String> {
    private final Set<String> whiteList;

    public ProposalValidator() {
        this.whiteList = new HashSet<>();
        this.whiteList.add("수락");
        this.whiteList.add("거절");
        this.whiteList.add("확정");
        this.whiteList.add("제안");
        this.whiteList.add(null);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return whiteList.contains(value);
    }
}
