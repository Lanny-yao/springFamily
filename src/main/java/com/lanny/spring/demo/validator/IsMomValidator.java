package com.lanny.spring.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * @author Lanny Yao
 * @date 9/17/2018 9:17 PM
 */
@Component
public class IsMomValidator implements ConstraintValidator<IsMom, String> {

    @Override
    public void initialize(IsMom constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith("mom-");
    }
}
