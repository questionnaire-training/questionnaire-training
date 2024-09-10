package training.questionnaire.repositories.jdbc;

import org.junit.jupiter.api.Test;
import training.questionnaire.repositories.TopicsRepository;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTestMysql
class TopicRepositoryJdbcTest extends AbstractMySqlTest {

    @Test
    void testSave(TopicsRepository topicsRepository) {
        long count = topicsRepository.count();
        String id = topicsRepository.save("Oracle Cloud Infrastructure Fundamentals");
        assertEquals(count + 1, topicsRepository.count());
        topicsRepository.deleteById(id);
        assertEquals(count, topicsRepository.count());
    }
}