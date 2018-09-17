package com.lanny.spring.demo.converter;

import com.lanny.spring.demo.converter.domain.DestinationModel;
import com.lanny.spring.demo.converter.domain.SourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Service;

/**
 * @author Lanny Yao
 * @date 9/17/2018 4:26 PM
 */
@Service
public class ConverterService {

    @Autowired
    private ConfigurableConversionService conversionService;

    public DestinationModel convert(SourceModel sourceModel) {
        return conversionService.convert(sourceModel, DestinationModel.class);
    }
}
