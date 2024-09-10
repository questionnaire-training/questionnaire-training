package training.questionnaire.views.question;

import io.micronaut.core.io.Writable;
import io.micronaut.http.HttpRequest;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.views.ViewsRenderer;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import training.questionnaire.Answer;
import training.questionnaire.Question;
import training.questionnaire.WriterUtils;
import training.questionnaire.repositories.IdGenerator;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@MicronautTest(startApplication = false)
class QuestionShowRenderTest {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionShowRenderTest.class);

    @Inject
    ViewsRenderer<Map<String, Object>, HttpRequest<?>> viewsRenderer;

    @Inject
    IdGenerator idGenerator;

    @Test
    void renderQuestionAsHtml() throws IOException {
        Question question = Question.builder()
                .id(idGenerator.generate())
                .name("Which statement about regions and availability domains is true?")
                .answer(Answer.builder().id(idGenerator.generate()).name("An OCI region has one or more availability domains.").correct().build())
                .answer(Answer.builder().id(idGenerator.generate()).name("All OCI regions have a single availability domain.").incorrect().build())
                .answer(Answer.builder().id(idGenerator.generate()).name("Fault domains provide protection against failures across regions.").incorrect().build())
                .answer(Answer.builder().id(idGenerator.generate()).name("All OCI regions have three availability domains.").incorrect().build())
                .build();

                Map<String, Object> model = Map.of("question", question);
                Writable writable = assertDoesNotThrow(() -> viewsRenderer.render("question/_question.html", model, null));
                String html = WriterUtils.asString(writable);
                LOG.trace(html);
    }
}
