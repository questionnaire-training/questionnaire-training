package training.questionnaire.repositories;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "spec.name", value = "TopicRepositoryTest")
@MicronautTest(startApplication = false)
class TopicRepositoryTest {

    @Test
    void saveNameParameterCannotBeBlank(TopicsRepository topicRepository) {
        assertThrows(ConstraintViolationException.class, () -> topicRepository.save(""));
    }

    @Test
    void deleteIdParameterCannotBeBlank(TopicsRepository topicRepository) {
        assertThrows(ConstraintViolationException.class, () -> topicRepository.deleteById(""));
        assertThrows(ConstraintViolationException.class, () -> topicRepository.deleteById(null));
    }

    @Requires(property = "spec.name", value = "TopicRepositoryTest")
    @Singleton
    @Replaces(TopicsRepository.class)
    static class CustomTopicRepository implements TopicsRepository {
        @Override
        public String save(@NotBlank String name) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public long count() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public void deleteById(String id) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
