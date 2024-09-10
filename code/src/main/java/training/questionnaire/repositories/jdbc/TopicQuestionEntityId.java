package training.questionnaire.repositories.jdbc;

import io.micronaut.data.annotation.Embeddable;
import io.micronaut.data.annotation.Relation;
import java.util.Objects;

@Embeddable
public class TopicQuestionEntityId {

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private final TopicEntity topic;

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private final QuestionEntity question;

    public TopicQuestionEntityId(TopicEntity topic, QuestionEntity question) {
        this.topic = topic;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TopicQuestionEntityId topicQuestionEntityId = (TopicQuestionEntityId) o;
        return question.id().equals(topicQuestionEntityId.getQuestion().id()) &&
                topic.id().equals(topicQuestionEntityId.getTopic().id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(question.id(), topic.id());
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public QuestionEntity getQuestion() {
        return question;
    }
}