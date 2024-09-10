package training.questionnaire.repositories.jdbc;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Property(name = "liquibase.datasources.default.change-log", value = "classpath:db/liquibase-changelog.xml")
@Property(name = "datasources.default.db-type", value = "mysql")
@Property(name = "datasources.default.dialect", value = "MYSQL")
@Property(name = "datasources.default.driver-class-name", value = "com.mysql.cj.jdbc.Driver")
@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers(disabledWithoutDocker = true)
public @interface MicronautTestMysql {
}
