package training.questionnaire;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Serdeable
public record Answer(@NonNull @NotBlank String id,
                     @NonNull @NotBlank String name,
                     @Nullable  String explanation,
                     boolean correct) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String explanation;
        boolean correct;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder explanation(String explanation) {
            this.explanation = explanation;
            return this;
        }

        public Builder correct(boolean correct) {
            this.correct = correct;
            return this;
        }

        public Builder correct() {
            this.correct = true;
            return this;
        }

        public Builder incorrect() {
            this.correct = false;
            return this;
        }

        public Answer build() {
            return new Answer(Objects.requireNonNull(id),
                    Objects.requireNonNull(name),
                    explanation,
                    correct);
        }
    }
}
