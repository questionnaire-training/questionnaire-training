package training.questionnaire.repositories.jdbc;

import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity("topics_questions")
public record TopicQuestionEntity(@EmbeddedId TopicQuestionEntityId id) {
}
