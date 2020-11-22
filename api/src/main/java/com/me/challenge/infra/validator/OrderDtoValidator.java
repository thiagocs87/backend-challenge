package com.me.challenge.infra.validator;

import com.me.challenge.infra.integration.dto.OrderDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
public class OrderDtoValidator implements BeanValidator<OrderDto>{

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Override
    public Set<ConstraintViolation<OrderDto>> validate(final OrderDto orderDto) {
        final Validator validator = factory.getValidator();
        return validator.validate(orderDto);
    }

}
