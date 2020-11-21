package com.me.challenge.config;


import com.me.challenge.domain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Configuration
public class DomainStatusValidatorConfig {

    @Bean
    public List<Validator> validatorsConfiguration(@Qualifier("itemValidator") final Validator itemValidator,
                                                   @Qualifier("totalPriceValidator") final Validator totalPriceValidator) {
        final List<Validator> validators = new ArrayList<>();
        validators.add(itemValidator);
        validators.add(totalPriceValidator);
        return Collections.unmodifiableList(validators);
    }

}
