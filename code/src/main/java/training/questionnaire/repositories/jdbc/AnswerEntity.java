package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import jakarta.validation.constraints.NotBlank;

@MappedEntity("answers")
public record AnswerEntity(@Id @NotBlank String id,
                            @NotBlank String name,
                           @Nullable String explanation,
                           boolean correct,
                           @Nullable @Relation(value = Relation.Kind.MANY_TO_ONE) @MappedProperty("questions_id") QuestionEntity question) {
}
