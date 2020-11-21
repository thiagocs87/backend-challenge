package com.me.challenge.config;

import com.me.challenge.infra.DefaultApplicationContextHolder;
import com.me.challenge.infra.MongoScript;
import com.me.challenge.infra.MongoScripts;
import net.minidev.json.JSONValue;
import org.bson.Document;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.ObjectUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.me.challenge.infra.MongoScript.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public class MongoScriptsExecutionConfiguration implements BeforeEachCallback {

    public void beforeEach(final ExtensionContext extensionContext) throws Exception {
        final MongoScripts annotation = getAnnotationMetadata(extensionContext);
        if (hasAnnotation(annotation)) {
            final MongoScript[] scripts = annotation.scripts();
            Stream.of(scripts).filter(s -> s.executionPhase().equals(BEFORE_TEST_METHOD)).forEach(script -> run(script));
        }
    }

    private MongoScripts getAnnotationMetadata(final ExtensionContext context) {
        final Method testMethod = context.getTestMethod().orElseThrow(() -> new RuntimeException("Is not a test method"));
        return testMethod.getAnnotation(MongoScripts.class);
    }

    private boolean hasAnnotation(final MongoScripts mongoScripts) {
        return ObjectUtils.isEmpty(mongoScripts) ? false : true;
    }


    private void run(final MongoScript script) {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(String.format("/%s", script.script()));
        final String json = JSONValue.parse(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8)).toString();

        final MongoTemplate mongoTemplate = DefaultApplicationContextHolder.getBean(MongoTemplate.class);
        final Document jsonDocument = Document.parse(json);

        for(Map.Entry<String, Object> entry : jsonDocument.entrySet()) {
            final List<Document> documents = (List<Document>)jsonDocument.get(entry.getKey());
            mongoTemplate.getDb().getCollection(entry.getKey()).insertMany(documents);
        }
    }
}
