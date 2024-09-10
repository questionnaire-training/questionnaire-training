package training.questionnaire.repositories;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record TopicQuestionSave(@NonNull @NotBlank String topicId,
                                @NonNull @NotBlank String questionId) {
}
