package training.questionnaire.controllers;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.views.fields.annotations.InputHidden;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record FormTopicQuestion(@InputHidden @NonNull @NotBlank String topicId,
                                @InputHidden @NonNull @NotBlank String questionId) {
}
