package com.lanny.spring.demo.validator.controller;

import com.lanny.spring.demo.validator.Child;
import com.lanny.spring.demo.validator.service.ValidateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lanny Yao
 * @date 9/17/2018 9:26 PM
 */
@RestController
@CrossOrigin(origins = {"*"})
public class ValidateController {

    @Autowired
    private ValidateService validateService;

    @PostMapping(value = "/validator")
    public List<String> validate(@RequestBody Child child) {
        List<String> result = validateService.validate(child);
        if (result.isEmpty()) {

            result.add("Validate successfully, " + child.getName() + " is a real " + child.getGender());
        }

        return result;
    }
}
