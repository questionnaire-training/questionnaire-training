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
import training.questionnaire.Question;
import training.questionnaire.repositories.QuestionSave;
import training.questionnaire.repositories.QuestionsRepository;

import java.net.URI;
import java.util.Optional;
import java.util.function.Function;
import static training.questionnaire.api.v1.Api.*;

@Controller(SLASH + API + SLASH + V1 + SLASH + QuestionsController.DOMAIN_QUESTIONS)
class QuestionsController {
    public static final String DOMAIN_QUESTIONS= "questions";
    private final QuestionsRepository questionsRepository;
    private static final Function<String, URI> URI_BUILDER_SHOW = id -> UriBuilder.of(SLASH + API).path(V1).path(DOMAIN_QUESTIONS).path(id).path(ACTION_SHOW).build();

    QuestionsController(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get(SLASH + ACTION_COUNT)
    @Produces(MediaType.TEXT_PLAIN)
    long count() {
        return questionsRepository.count();
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Post
    HttpResponse<?> save(@Body @NonNull @NotNull @Valid QuestionSave questionSave) {
        String id = questionsRepository.save(questionSave.name());
        return HttpResponse.created(URI_BUILDER_SHOW.apply(id));
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    void delete(@PathVariable @NonNull @NotBlank String id) {
        questionsRepository.deleteById(id);
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get(SLASH + PATH_VARIABLE_ID + SLASH + ACTION_SHOW)
    Optional<Question> get(@PathVariable @NonNull @NotBlank String id) {
        return questionsRepository.get(id);
    }
}
