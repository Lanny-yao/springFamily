package com.lanny.spring.demo.converter.controller;

import com.lanny.spring.demo.converter.ConverterService;
import com.lanny.spring.demo.converter.domain.DestinationModel;
import com.lanny.spring.demo.converter.domain.SourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lanny Yao
 * @date 9/17/2018 4:25 PM
 */
@RestController
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @PostMapping("/converter")
    public DestinationModel convert(@RequestBody SourceModel sourceModel) {
        return converterService.convert(sourceModel);
    }


}
