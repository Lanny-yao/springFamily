package com.lanny.spring.demo.validator.service;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import com.lanny.spring.demo.validator.Child;
import com.lanny.spring.demo.validator.Child.Boy;
import com.lanny.spring.demo.validator.Child.Girl;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lanny Yao
 * @date 9/17/2018 9:25 PM
 */
@Slf4j
@Service
public class ValidateService {

    @Autowired
    private Validator validator;

    public List<String> validate(Child child) {
        String gender = child.getGender();

        Set<ConstraintViolation<Child>> validate;
        if ("boy".equals(gender)) {
            validate = validator.validate(child, Boy.class);
        } else {
            validate = validator.validate(child, Girl.class);
        }

        return validate.stream().map(ConstraintViolation::getMessage).collect(toList());
    }
}
