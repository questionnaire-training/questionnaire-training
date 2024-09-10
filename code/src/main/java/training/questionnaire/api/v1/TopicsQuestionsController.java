package training.questionnaire.api.v1;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import training.questionnaire.repositories.TopicQuestionSave;
import training.questionnaire.repositories.TopicsQuestionsRepository;

import static training.questionnaire.api.v1.Api.*;
import static training.questionnaire.api.v1.Api.SLASH;

@Controller
class TopicsQuestionsController {

    public static final String PATH_TOPICS_QUESTIONS = SLASH + API + SLASH + V1 + SLASH + TopicsController.DOMAIN_TOPICS + SLASH + QuestionsController.DOMAIN_QUESTIONS;
    private final TopicsQuestionsRepository topicsQuestionsRepository;

    TopicsQuestionsController(TopicsQuestionsRepository topicsQuestionsRepository) {
        this.topicsQuestionsRepository = topicsQuestionsRepository;
    }

    @Post(PATH_TOPICS_QUESTIONS)
    @Status(HttpStatus.NO_CONTENT)
    void save(@Body @NonNull  @NotNull @Valid TopicQuestionSave topicQuestionSave) {
        topicsQuestionsRepository.save(topicQuestionSave);
    }
}
