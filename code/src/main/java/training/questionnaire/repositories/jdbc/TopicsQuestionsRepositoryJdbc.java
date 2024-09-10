package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import training.questionnaire.exceptions.QuestionNotFoundException;
import training.questionnaire.exceptions.TopicNotFoundException;
import training.questionnaire.repositories.TopicQuestionSave;
import training.questionnaire.repositories.TopicsQuestionsRepository;

@Singleton
class TopicsQuestionsRepositoryJdbc implements TopicsQuestionsRepository {
    private final TopicsCrudRepository topicsCrudRepository;
    private final QuestionsCrudRepository questionsCrudRepository;
    private final TopicQuestionCrudRepository topicQuestionCrudRepository;

    TopicsQuestionsRepositoryJdbc(TopicsCrudRepository topicsCrudRepository,
                                  QuestionsCrudRepository questionsCrudRepository,
                                  TopicQuestionCrudRepository topicQuestionCrudRepository) {
        this.topicsCrudRepository = topicsCrudRepository;
        this.questionsCrudRepository = questionsCrudRepository;
        this.topicQuestionCrudRepository = topicQuestionCrudRepository;
    }

    @Override
    public void save(@NonNull @Valid TopicQuestionSave topicQuestionSave) {
        TopicEntity topic = topicsCrudRepository.findById(topicQuestionSave.topicId()).orElseThrow(TopicNotFoundException::new);
        QuestionEntity question = questionsCrudRepository.findById(topicQuestionSave.questionId()).orElseThrow(QuestionNotFoundException::new);
        topicQuestionCrudRepository.save(new TopicQuestionEntity(new TopicQuestionEntityId(topic, question)));
    }
}
