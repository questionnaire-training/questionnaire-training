package training.questionnaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Question(String title, List<Answer> answers) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private List<Answer> answers;
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder answer(Answer answer) {
            if (answers == null) {
                answers = new ArrayList<>();
            } else {
                answers.add(answer);
            }
            return this;
        }

        public Question build() {
            return new Question(Objects.requireNonNull(title),
                    answers == null ? Collections.emptyList() : answers);
        }
    }
}
