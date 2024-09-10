package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@MappedEntity("questions")
public record QuestionEntity(@Id @NonNull@NotBlank String id,
                             @NonNull @NotBlank String name,
                             @Nullable @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "question") Set<AnswerEntity> answers) {
}