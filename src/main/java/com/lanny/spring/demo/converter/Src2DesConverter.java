package com.lanny.spring.demo.converter;

import com.lanny.spring.demo.converter.domain.DestinationModel;
import com.lanny.spring.demo.converter.domain.SourceModel;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Lanny Yao
 * @date 9/17/2018 4:16 PM
 */
public class Src2DesConverter implements Converter<SourceModel, DestinationModel> {

    /**
     * self define convert strategy
     */
    @Override
    public DestinationModel convert(SourceModel source) {
        return DestinationModel.builder()
                .desAge(source.getSrcAge() + 1)
                .desName(source.getSrcName() + "-des")
                .build();
    }
}
