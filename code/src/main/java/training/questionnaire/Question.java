package training.questionnaire;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Serdeable
public record Question(@NonNull @NotBlank String id,
                       @NonNull @NotBlank String name,
                       @Nullable List<Answer> answers) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private List<Answer> answers;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder answer(Answer answer) {
            if (answers == null) {
                answers = new ArrayList<>();
            }
            answers.add(answer);
            return this;
        }

        public Question build() {
            return new Question(Objects.requireNonNull(id),
                    Objects.requireNonNull(name),
                    answers == null ? Collections.emptyList() : answers);
        }
    }
}
