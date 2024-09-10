package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.support.TestPropertyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public abstract class AbstractMySqlTest implements TestPropertyProvider {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMySqlTest.class);

    private static final DockerImageName MYSQL_80_IMAGE = DockerImageName.parse("mysql:8.0.36");

    @Container
    private static final MySQLContainer<?> db = new MySQLContainer<>(MYSQL_80_IMAGE)
            .withLogConsumer(new Slf4jLogConsumer(LOG))
            .withReuse(true);


    @Override
    public @NonNull Map<String, String> getProperties() {
        if (!db.isRunning()) {
            db.start();
        }
        return  Map.of(
                "datasources.default.url", db.getJdbcUrl(),
                "datasources.default.username", db.getUsername(),
                "datasources.default.password", db.getPassword());
    }
}
