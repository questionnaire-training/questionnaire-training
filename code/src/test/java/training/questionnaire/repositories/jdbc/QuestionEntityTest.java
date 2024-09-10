package training.questionnaire.repositories.jdbc;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class QuestionEntityTest {
    @Inject
    Validator validator;

    @Test
    void idCanNotBeNull() {
        QuestionEntity entity = new QuestionEntity(null, "name", null);
        assertFalse(validator.validate(entity).isEmpty());
    }

    @Test
    void nameCannotBeBlank() {
        QuestionEntity entity = new QuestionEntity("xxx", "", null);
        assertFalse(validator.validate(entity).isEmpty());
        entity = new QuestionEntity("xxx", null, null);
        assertFalse(validator.validate(entity).isEmpty());
    }
}