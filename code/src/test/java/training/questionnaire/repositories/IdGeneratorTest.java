package training.questionnaire.repositories;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class IdGeneratorTest {

    @Test
    void testGenerate(IdGenerator idGenerator) {
        Set<String> ids = new HashSet<>();
        int expectedSize = 1000;
        for (int i = 0; i < expectedSize; i++) {
            String id = idGenerator.generate();
            ids.add(id);
        }
        assertEquals(expectedSize, ids.size());
    }
}