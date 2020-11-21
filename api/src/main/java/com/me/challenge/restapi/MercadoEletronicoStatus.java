package com.me.challenge.restapi;

import com.me.challenge.infra.integration.dto.StatusDto;
import com.me.challenge.infra.integration.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class MercadoEletronicoStatus {

    private final StatusService service;

    @PostMapping(consumes="application/json")
    public ResponseEntity<StatusDto> createOrder(@RequestBody final StatusDto statusDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.process(statusDto));
    }
}
