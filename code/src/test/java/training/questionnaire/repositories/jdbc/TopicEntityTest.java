package training.questionnaire.repositories.jdbc;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class TopicEntityTest {

    @Inject
    Validator validator;

    @Test
    void idCanBeNull() {
        TopicEntity topicEntity = new TopicEntity(null, "name");
        assertTrue(validator.validate(topicEntity).isEmpty());
    }

    @Test
    void nameCannotBeBlank() {
        TopicEntity topicEntity = new TopicEntity(null, "");
        assertFalse(validator.validate(topicEntity).isEmpty());
        topicEntity = new TopicEntity(null, null);
        assertFalse(validator.validate(topicEntity).isEmpty());
    }

}