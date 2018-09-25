package com.lanny.spring.demo.validator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author Lanny Yao
 * @date 9/17/2018 9:06 PM
 */
@Data
public class Child {

    public interface Boy {

    }

    public interface Girl {

    }

    @NotNull(message = "Child's name may not be present", groups = {Boy.class, Girl.class})
    @Size(message = "name length must be between {min} and {max}", min = 1, max = 64, groups = {Boy.class, Girl.class})
    private String name;

    @Min(value = 0, message = "Child's age may not lower than 0 years old", groups = {Boy.class, Girl.class})
    @Max(value = 10, message = "Child's age may not higher than 10 years old", groups = {Boy.class, Girl.class})
    private Integer age;

    /**
     * only mom's name is accepted
     */
    @IsMom(groups = {Boy.class, Girl.class})
    private String parentName;

    @Null(message = "A boy will not have a dress...", groups = Boy.class)
    @NotNull(message = "A girl must have a dress", groups = Girl.class)
    private String dressColor;

    @NotNull(message = "Gender is a mandatory field")
    private String gender;

}
