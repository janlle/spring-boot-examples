package com.andy.batch.process;

import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * <p> 我们可以JSR-303(主要实现由hibernate-validator)的注解，来校验ItemReader读取到的数据是否满足要求。我们可以让我们的ItemProcessor实现ValidatingItemProcessor接口
 *
 * @author Leone
 * @since 2018-10-09
 **/
public class CsvBeanValidator<T> implements Validator<T>, InitializingBean {

    private javax.validation.Validator validator;


    // 使用JSR-303的Validator来校验我们的数据，在此处进行JSR-303的Validator的初始化。
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    // 使用Validator的validate方法校验数据。
    @Override
    public void validate(T value) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(value);
        if (constraintViolations.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                message.append(constraintViolation.getMessage() + "\n");
            }
            throw new ValidationException(message.toString());
        }
    }
}
