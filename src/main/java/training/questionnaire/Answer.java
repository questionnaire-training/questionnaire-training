package training.questionnaire;

import java.util.Objects;

public record Answer(String title, boolean correct) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        boolean correct;

        public Builder title(String title) {
            this.title = title;
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
            return new Answer(Objects.requireNonNull(title),
                    correct);
        }
    }
}
