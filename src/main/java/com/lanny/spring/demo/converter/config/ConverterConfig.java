package com.lanny.spring.demo.converter.config;

import com.lanny.spring.demo.converter.Src2DesConverter;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;

/**
 * @author Lanny Yao
 * @date 9/17/2018 4:20 PM
 */
@Configuration
public class ConverterConfig {

    @Autowired
    private ConfigurableConversionService conversionService;

    @Bean
    @Primary
    public ConfigurableConversionService getConversionService() {
        return conversionService;
    }

    @PostConstruct
    public void addConverters() {
        //register converter
        conversionService.addConverter(new Src2DesConverter());
    }

}
