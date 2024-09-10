package training.questionnaire.repositories;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable.Deserializable
public record AnswerSave(@NonNull @NotBlank String questionId,
                         @NonNull @NotBlank String name,
                         @Nullable String explanation,
                         boolean correct) {
}
