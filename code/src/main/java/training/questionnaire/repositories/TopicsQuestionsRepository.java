package training.questionnaire.repositories;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface TopicsQuestionsRepository {
    void save(@NonNull @NotNull @Valid TopicQuestionSave topicQuestionSave);
}
