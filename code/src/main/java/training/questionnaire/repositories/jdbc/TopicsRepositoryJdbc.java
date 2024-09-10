package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import training.questionnaire.repositories.IdGenerator;
import training.questionnaire.repositories.Topic;
import training.questionnaire.repositories.TopicsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
class TopicsRepositoryJdbc implements TopicsRepository {

    private final TopicMappers topicMappers;
    private final TopicsCrudRepository topicCrudRepository;
    private final IdGenerator idGenerator;

    TopicsRepositoryJdbc(TopicMappers topicMappers,
                         TopicsCrudRepository topicCrudRepository,
                         IdGenerator idGenerator) {
        this.topicMappers = topicMappers;
        this.topicCrudRepository = topicCrudRepository;
        this.idGenerator = idGenerator;
    }


    @Override
    public String save(String name) {
        return topicCrudRepository.save(new TopicEntity(idGenerator.generate(), name)).id();
    }

    @Override
    public long count() {
        return topicCrudRepository.count();
    }

    @Override
    public void deleteById(String id) {
        topicCrudRepository.deleteById(id);
    }
}
