package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.NotBlank;

@MappedEntity("topics")
public record TopicEntity(@Id @Nullable String id,
                          @NonNull @NotBlank String name) {
}