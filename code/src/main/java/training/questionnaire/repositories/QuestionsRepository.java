package training.questionnaire.repositories;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;
import training.questionnaire.Question;

import java.util.Optional;

public interface QuestionsRepository {
    long count();

    @NonNull
    String save(@NonNull @NotBlank String name);

    void deleteById(@NonNull @NotBlank String id);

    @NonNull
    Optional<Question> get(@NonNull @NotBlank String id);
}
