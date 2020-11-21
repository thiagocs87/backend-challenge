package com.me.challenge.infra.integration.service;

import com.me.challenge.infra.integration.dto.StatusDto;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public interface StatusService {

    StatusDto process(StatusDto statusDto);
}
