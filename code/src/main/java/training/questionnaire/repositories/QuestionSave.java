package training.questionnaire.repositories;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable.Deserializable
public record QuestionSave(@NonNull @NotBlank String name) {
}
