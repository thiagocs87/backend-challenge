package com.me.challenge.infra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.challenge.config.MongoScriptsExecutionConfiguration;
import lombok.val;
import net.minidev.json.JSONValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@ActiveProfiles("test")
@ExtendWith({MongoScriptsExecutionConfiguration.class})
@DirtiesContext
public class AbstractIntegrationTests {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected MongoTemplate mongoTemplate;

    @AfterEach
    private void clear() {
        final MongoTemplate mongoTemplate = DefaultApplicationContextHolder.getBean(MongoTemplate.class);
        mongoTemplate.getCollectionNames()
                .forEach(name -> mongoTemplate.dropCollection(name));
    }

    protected String getMvcResultAsString(final MvcResult mvcResult) throws UnsupportedEncodingException {
        final MockHttpServletResponse response = mvcResult.getResponse();
        return response.getContentAsString();
    }

    protected JsonNode getJsonNodeFromMvcResult(final MvcResult mvcResult) throws IOException {
        val string = getMvcResultAsString(mvcResult);
        return objectMapper.readTree(string);
    }

    protected String getTextPlainFileAsString(final String path) {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(String.format("/%s", path));
        String text = null;
        try (Scanner scanner = new Scanner(resourceAsStream, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }
        return text;
    }

}
