package com.me.challenge.infra.validator;

import com.me.challenge.infra.integration.dto.StatusDto;
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
public class StatusDtoValidator implements BeanValidator<StatusDto>{

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Override
    public Set<ConstraintViolation<StatusDto>> validate(final StatusDto statusDto) {
        final Validator validator = factory.getValidator();
        return validator.validate(statusDto);
    }

}
