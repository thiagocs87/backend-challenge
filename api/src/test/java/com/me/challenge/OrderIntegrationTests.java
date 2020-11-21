package com.me.challenge;

import com.fasterxml.jackson.databind.JsonNode;
import com.me.challenge.domain.Order;
import com.me.challenge.domain.repository.OrderRepository;
import com.me.challenge.infra.AbstractIntegrationTests;
import com.me.challenge.infra.MongoScript;
import com.me.challenge.infra.MongoScripts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@SpringBootTest(classes = {ChallengeApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderIntegrationTests extends AbstractIntegrationTests {

    private final String ENDPOINT = "/api/pedido";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository repository;

    @Test
    public void givenAValidPostRequestInput_WhenReceived_ThenShouldPersistAndReturnCreated() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validRequest.json");

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        final Order order = repository.findAll().get(0);
        assertEquals(order.getOrderId(), "123456");
    }

    @Test
    public void givenAnIValidPostRequestInput_WhenReceived_ThenShouldReturnBadRequest() throws Exception {

        final String invalidRequest = getTextPlainFileAsString("data/request/invalidRequest.json");

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidDeleteInputRequestWithAnExistingOrder_WhenReceived_ThenShouldReturnOrderAndStatusFound() throws Exception {

        mockMvc.perform(delete(ENDPOINT.concat("/123456"))).andExpect(status().isOk()).andReturn();

        assertTrue(Objects.isNull(repository.findByOrderId("123456").orElse(null)));
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidGetRequestInputWithAnExistingOrder_WhenReceived_ThenShouldReturnOrderAndStatusFound() throws Exception {

        final String validResponse = getTextPlainFileAsString("data/response/validResponse.json");

        final MvcResult mvcResult = mockMvc.perform(get(ENDPOINT.concat("/123456"))).andExpect(status().isFound()).andReturn();;
        final JsonNode jsonNode = getJsonNodeFromMvcResult(mvcResult);

        assertTrue(validResponse.equalsIgnoreCase(jsonNode.toString()));
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidGetRequestWithAnNonExistingOrderInput_WhenReceived_ThenShouldReturnNotFound() throws Exception {

        mockMvc.perform(get(ENDPOINT.concat("/12345678"))).andExpect(status().isNotFound());
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidPutRequestInput_WhenReceived_ThenShouldPersistAndReturnOk() throws Exception {

        final Order order = repository.findByOrderId("1234567").get();

        final String validRequest = getTextPlainFileAsString("data/request/validPutRequest.json");

        mockMvc.perform(put(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());

        final Order order1 = repository.findByOrderId("1234567").get();
        assertEquals(order1.getTotalItems(), 3);
    }

    @Test
    public void givenAValidNonExistingOrderPutRequestInput_WhenReceived_ThenShouldPersistAndReturnOk() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validPutRequest.json");

        mockMvc.perform(put(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAnInValidExistingOrderPutRequestInput_WhenReceived_ThenShouldReturnBadRequest() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/invalidRequest.json");

        mockMvc.perform(put(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
