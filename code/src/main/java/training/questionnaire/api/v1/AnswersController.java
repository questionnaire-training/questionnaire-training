package training.questionnaire.api.v1;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import training.questionnaire.repositories.AnswerSave;
import training.questionnaire.repositories.AnswersRepository;
import training.questionnaire.repositories.QuestionSave;
import training.questionnaire.repositories.QuestionsRepository;

import java.net.URI;
import java.util.function.Function;

import static training.questionnaire.api.v1.Api.*;

@Controller
class AnswersController {
    public static final String DOMAIN_ANSWERS= "answers";
    private static final String PATH_ANSWERS = SLASH + API + SLASH + V1 + SLASH + QuestionsController.DOMAIN_QUESTIONS + SLASH + "{questionId}" + SLASH + DOMAIN_ANSWERS;
    private final AnswersRepository answersRepository;

    AnswersController(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Post(PATH_ANSWERS)
    HttpResponse<?> save(@PathVariable String questionId,
                         @Body @NonNull @NotNull @Valid AnswerSave answerSave) {
        if (!questionId.equals(answerSave.questionId())) {
            return HttpResponse.unprocessableEntity();
        }
        answersRepository.save(answerSave);
        return HttpResponse.noContent();
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Delete(PATH_ANSWERS + SLASH + PATH_VARIABLE_ID)
    @Status(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String questionId, @PathVariable @NonNull @NotBlank String id) {
        answersRepository.deleteByQuestionIdAndId(questionId, id);
    }
}
