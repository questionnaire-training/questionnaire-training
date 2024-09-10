package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import training.questionnaire.Answer;
import training.questionnaire.Question;
import training.questionnaire.repositories.IdGenerator;
import training.questionnaire.repositories.QuestionsRepository;

import java.util.Optional;

@Singleton
public class QuestionsRepositoryJdbc implements QuestionsRepository {

    private final AnswersCrudRepository answersCrudRepository;
    private final QuestionsCrudRepository questionsCrudRepository;
    private final IdGenerator idGenerator;

    public QuestionsRepositoryJdbc(AnswersCrudRepository answersCrudRepository, QuestionsCrudRepository questionsCrudRepository, IdGenerator idGenerator) {
        this.answersCrudRepository = answersCrudRepository;
        this.questionsCrudRepository = questionsCrudRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public long count() {
        return questionsCrudRepository.count();
    }

    @Override
    @NonNull
    public String save(@NonNull @NotBlank String name) {
        return questionsCrudRepository.save(new QuestionEntity(idGenerator.generate(), name, null)).id();
    }

    @Override
    public void deleteById(@NonNull @NotBlank String id) {
        questionsCrudRepository.deleteById(id);
    }

    @Override
    public Optional<Question> get(@NonNull @NotBlank String id) {
        return questionsCrudRepository.findById(id)
                .map(questionEntity -> {
                    Question.Builder builder = Question.builder()
                            .id(questionEntity.id())
                            .name(questionEntity.name());
                    for (AnswerEntity answerEntity : answersCrudRepository.findAllByQuestion(questionEntity)) {
                        builder.answer(Answer.builder()
                                .id(answerEntity.id())
                                .name(answerEntity.name())
                                .correct(answerEntity.correct())
                                .build());
                    }
                    return builder.build();
                });
    }

}
