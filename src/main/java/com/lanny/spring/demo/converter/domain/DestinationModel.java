package com.lanny.spring.demo.converter.domain;

import lombok.Builder;
import lombok.Setter;

/**
 * @author Lanny Yao
 * @date 9/17/2018 4:15 PM
 */
@Setter
@Builder
public class DestinationModel {

    private String desName;
    private Integer desAge;

}
