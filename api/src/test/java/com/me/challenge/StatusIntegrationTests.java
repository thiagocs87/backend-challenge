package com.me.challenge;

import com.fasterxml.jackson.databind.JsonNode;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@SpringBootTest(classes = {ChallengeApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StatusIntegrationTests extends AbstractIntegrationTests {


    private final String ENDPOINT = "/api/status";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidApprovedPostRequestInput_WhenReceived_ThenShouldReturnOkWithAValidResponse() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validApprovedRequestStatus.json");
        final String validResponse = getTextPlainFileAsString("data/response/approvedStatusResponse.json");

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        final JsonNode jsonNode = getJsonNodeFromMvcResult(mvcResult);
        assertTrue(validResponse.equalsIgnoreCase(jsonNode.toString()));
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidApprovedWithLowerValuePostRequest_WhenReceived_ThenShouldReturnOkWithAValidResponse() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validApprovedLowerValueRequestStatus.json");
        final String validResponse = getTextPlainFileAsString("data/response/approvedLowerValueStatusResponse.json");

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        final JsonNode jsonNode = getJsonNodeFromMvcResult(mvcResult);
        assertTrue(validResponse.equalsIgnoreCase(jsonNode.toString()));
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidApprovedWithGreaterValueAndQuantityValuePostRequest_WhenReceived_ThenShouldReturnOkWithAValidResponse() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validApprovedGreaterRequestStatus.json");
        final String validResponse = getTextPlainFileAsString("data/response/approvedGreaterStatusResponse.json");

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        final JsonNode jsonNode = getJsonNodeFromMvcResult(mvcResult);
        assertTrue(validResponse.equalsIgnoreCase(jsonNode.toString()));
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidApprovedWithLowerQuantityPostRequest_WhenReceived_ThenShouldReturnOkWithAValidResponse() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validApprovedLowerQuantityRequestStatus.json");
        final String validResponse = getTextPlainFileAsString("data/response/approvedLowerQuantityStatusResponse.json");

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        final JsonNode jsonNode = getJsonNodeFromMvcResult(mvcResult);
        assertTrue(validResponse.equalsIgnoreCase(jsonNode.toString()));
    }

    @Test
    @MongoScripts(scripts = {@MongoScript(script = "data/mongo/mongo_data.json",
            executionPhase = MongoScript.ExecutionPhase.BEFORE_TEST_METHOD)})
    public void givenAValidDisapprovedRequest_WhenReceived_ThenShouldReturnOkWithAValidResponse() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validDisapprovedRequestStatus.json");
        final String validResponse = getTextPlainFileAsString("data/response/disapprovedStatusResponse.json");

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        final JsonNode jsonNode = getJsonNodeFromMvcResult(mvcResult);
        assertTrue(validResponse.equalsIgnoreCase(jsonNode.toString()));
    }

    @Test
    public void givenAValidApprovedNonExistingOrderPostRequest_WhenReceived_ThenShouldReturnOkWithAValidResponse() throws Exception {

        final String validRequest = getTextPlainFileAsString("data/request/validNonExistingRequestStatus.json");
        final String validResponse = getTextPlainFileAsString("data/response/invalidCodeStatusResponse.json");

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        final JsonNode jsonNode = getJsonNodeFromMvcResult(mvcResult);
        assertTrue(validResponse.equalsIgnoreCase(jsonNode.toString()));
    }
}
