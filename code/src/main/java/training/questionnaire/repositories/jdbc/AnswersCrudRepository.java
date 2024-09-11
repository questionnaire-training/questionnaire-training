package training.questionnaire.repositories.jdbc;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface AnswersCrudRepository extends CrudRepository<AnswerEntity, String> {
    List<AnswerEntity> findAllByQuestion(QuestionEntity question);
}
