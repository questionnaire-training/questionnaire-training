package training.questionnaire.controllers;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.views.fields.annotations.InputHidden;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record FormAnswerSave(@InputHidden @NonNull @NotBlank String questionId,
                             @NonNull @NotBlank String name,
                             boolean correct) {
}
