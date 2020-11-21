package com.me.challenge.restapi;

import com.me.challenge.infra.integration.dto.OrderDto;
import com.me.challenge.infra.integration.service.OrderService;
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
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class MercadoEletronicoOrder {

    private final OrderService service;

    @PostMapping(consumes="application/json")
    public ResponseEntity<?> createOrder(@RequestBody final OrderDto orderDto) {
        service.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(consumes="application/json")
    public ResponseEntity<?> changeOrder(@RequestBody final OrderDto orderDto) {
        service.updateOrder(orderDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<?> removeOrder(@PathVariable("orderId") final String id) {
        service.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDto> findOrder(@PathVariable("orderId")  final String id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getOrderDto(id));
    }
}
