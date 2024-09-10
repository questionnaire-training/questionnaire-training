package training.questionnaire.repositories.jdbc;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import training.questionnaire.exceptions.QuestionNotFoundException;
import training.questionnaire.repositories.AnswerSave;
import training.questionnaire.repositories.AnswersRepository;
import training.questionnaire.repositories.IdGenerator;

@Singleton
class AnswersRepositoryJdbc implements AnswersRepository {
    private final IdGenerator idGenerator;
    private final AnswersCrudRepository answersCrudRepository;
    private final QuestionsCrudRepository questionsCrudRepository;

    public AnswersRepositoryJdbc(IdGenerator idGenerator, AnswersCrudRepository answersCrudRepository, QuestionsCrudRepository questionsCrudRepository) {
        this.idGenerator = idGenerator;
        this.answersCrudRepository = answersCrudRepository;
        this.questionsCrudRepository = questionsCrudRepository;
    }

    @Override
    public String save(@NonNull @NotNull @Valid AnswerSave answerSave) {
        return questionsCrudRepository.findById(answerSave.questionId())
                .map(questionEntity -> answersCrudRepository.save(new AnswerEntity(idGenerator.generate(), answerSave.name(), answerSave.explanation(), answerSave.correct(), questionEntity)).id())
                .orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public void deleteByQuestionIdAndId(@NonNull @NotBlank String questionId,
                                        @NonNull @NotBlank String answerId) {
        answersCrudRepository.deleteById(answerId);
    }
}
