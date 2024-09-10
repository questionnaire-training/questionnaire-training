package training.questionnaire.repositories.jdbc;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface TopicQuestionCrudRepository extends CrudRepository<TopicQuestionEntity, TopicQuestionEntityId> {
}
