package training.questionnaire.repositories;

import io.micronaut.json.JsonMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class TopicTest {

    @Test
    void topicJsonSerialization(JsonMapper jsonMapper) throws IOException {
        Topic topic = new Topic("1", "Oracle Cloud Infrastructure Fundamentals");
        String json = jsonMapper.writeValueAsString(topic);
        assertEquals("""
    {"id":"1","name":"Oracle Cloud Infrastructure Fundamentals"}""", json);
    }
}