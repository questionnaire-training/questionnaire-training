package training.questionnaire.repositories.jdbc;

import io.micronaut.context.annotation.Mapper;
import training.questionnaire.repositories.Topic;

public interface TopicMappers {
    @Mapper
    TopicEntity toEntity(Topic topic);
    Topic fromEntity(TopicEntity topicEntity);
}
