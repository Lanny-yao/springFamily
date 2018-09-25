package com.lanny.spring.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

/**
 * @author Lanny Yao
 * @date 9/17/2018 9:14 PM
 */
@NotNull(message = "parent may not be null")
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsMomValidator.class)
public @interface IsMom {

    String message() default "Parent is mom only when parent name starts with 'mom-'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
