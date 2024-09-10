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
import training.questionnaire.repositories.TopicSave;
import training.questionnaire.repositories.TopicsRepository;

import java.net.URI;
import java.util.function.Function;
import static training.questionnaire.api.v1.Api.*;

@Controller(TopicsController.PATH_TOPICS)
class TopicsController {
    public static final String PATH_TOPICS = SLASH + API + SLASH + V1 + SLASH + TopicsController.DOMAIN_TOPICS;
    public static final String DOMAIN_TOPICS = "topics";
    private final TopicsRepository topicsRepository;
    private static final Function<String, URI> URI_BUILDER_SHOW = id -> UriBuilder.of(SLASH + API).path(V1).path(DOMAIN_TOPICS).path(id).path(ACTION_SHOW).build();

    TopicsController(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get(SLASH + ACTION_COUNT)
    @Produces(MediaType.TEXT_PLAIN)
    long count() {
        return topicsRepository.count();
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Post
    HttpResponse<?> save(@Body @NonNull @NotNull @Valid TopicSave topicSave) {
        String id = topicsRepository.save(topicSave.name());
        return HttpResponse.created(URI_BUILDER_SHOW.apply(id));
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    void delete(@PathVariable @NonNull @NotBlank String id) {
        topicsRepository.deleteById(id);
    }
}
