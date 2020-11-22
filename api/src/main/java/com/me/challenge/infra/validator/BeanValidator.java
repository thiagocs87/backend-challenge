package com.me.challenge.infra.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@FunctionalInterface
public interface BeanValidator<S>{

    Set<ConstraintViolation<S>> validate(S s);

}
