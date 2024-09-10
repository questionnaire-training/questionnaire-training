package training.questionnaire.repositories;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface AnswersRepository {
    String save(@NonNull @NotNull @Valid AnswerSave answerSave);

    void deleteByQuestionIdAndId(@NonNull @NotBlank String questionId, @NonNull @NotBlank String answerId);
}
