package training.questionnaire.repositories;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;

public interface TopicsRepository {
    @NotBlank
    @NonNull
    String save(@NonNull @NotBlank String name);

    long count();

    void deleteById(@NonNull @NotBlank String id);
}
