package training.questionnaire.repositories;

import io.micronaut.core.type.Argument;
import io.micronaut.json.JsonMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class TopicSaveTest {
    @Test
    void topicSaveJsonDeserialization(JsonMapper jsonMapper) throws IOException {
        TopicSave topic = new TopicSave("Oracle Cloud Infrastructure Fundamentals");
        assertEquals(jsonMapper.readValue("""
    {"name":"Oracle Cloud Infrastructure Fundamentals"}""", Argument.of(TopicSave.class)), topic);
    }
}