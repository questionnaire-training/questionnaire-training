package training.questionnaire.api.v1;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import org.junit.jupiter.api.Test;
import training.questionnaire.Answer;
import training.questionnaire.Question;
import training.questionnaire.repositories.jdbc.AbstractMySqlTest;
import training.questionnaire.repositories.jdbc.MicronautTestMysql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTestMysql
class AnswersControllerTest extends AbstractMySqlTest {

    @Test
    void answerCrud(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        long count = assertDoesNotThrow(() -> client.retrieve("/api/v1/questions/count", Long.class));
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/questions", "{\"name\":\"Which statement about OCI compartments is NOT true?\"}")));
        assertEquals(HttpStatus.CREATED, response.getStatus());
        String location = response.header(HttpHeaders.LOCATION);
        assertTrue(location.startsWith("/api/v1/questions/"));
        assertTrue(location.endsWith("/show"));
        assertEquals(count + 1 , assertDoesNotThrow(() -> client.retrieve("/api/v1/questions/count", Long.class)));
        String questionId = location.substring(location.indexOf("/api/v1/questions/") + "/api/v1/questions/".length(), location.indexOf("/show"));

         HttpResponse<?> answer1Response = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/questions/" + questionId + "/answers", "{\"questionId\": \"" + questionId + "\",\"name\":\"It is a best practice to create all your resources in the root compartment.\",\"correct\":true}")));
         assertEquals(HttpStatus.NO_CONTENT, answer1Response.getStatus());

        HttpResponse<?> answer2Response = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/questions/" + questionId + "/answers", "{\"questionId\": \"" + questionId + "\",\"name\":\"A compartment is a logical collection of related resources.\",\"correct\":false}")));
        assertEquals(HttpStatus.NO_CONTENT, answer2Response.getStatus());

        HttpResponse<?> answer3Response = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/questions/" + questionId + "/answers", "{\"questionId\": \"" + questionId + "\",\"name\":\"Compartments help to isolate and control access to resources..\",\"correct\":false}")));
        assertEquals(HttpStatus.NO_CONTENT, answer3Response.getStatus());

        HttpResponse<?> answer4Response = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/questions/" + questionId + "/answers", "{\"questionId\": \"" + questionId + "\",\"name\":\"Compartments can be nested.\",\"correct\":false}")));
        assertEquals(HttpStatus.NO_CONTENT, answer4Response.getStatus());

        HttpResponse<Question> questionGetResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.GET("/api/v1/questions/" + questionId + "/show"), Question.class));
        assertEquals(HttpStatus.OK, questionGetResponse.getStatus());
        Optional<Question> questionOptional = questionGetResponse.getBody(Question.class);
        assertTrue(questionOptional.isPresent());
        Question question = questionOptional.get();
        assertNotNull(question);

        assertNotNull(question.answers());
        assertTrue(question.answers().stream().allMatch(a -> a.id() != null));
        assertNotNull(question.id());
        assertEquals("Which statement about OCI compartments is NOT true?", question.name());
        assertEquals(4, question.answers().size());
        assertEquals(1, question.answers().stream().filter(Answer::correct).toList().size());
//        assertTrue(question.answers().stream().anyMatch(a -> a.name().equals("It is a best practice to create all your resources in the root compartment.")));
//        assertTrue(question.answers().stream().anyMatch(a -> a.name().equals("Compartments help to isolate and control access to resources.")));
//        assertTrue(question.answers().stream().anyMatch(a -> a.name().equals("Compartments can be nested.")));
//        assertTrue(question.answers().stream().anyMatch(a -> a.name().equals("A compartment is a logical collection of related resources.")));

        question.answers().stream().map(Answer::id).forEach(answerId -> {
            HttpResponse<?> deleteResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.DELETE("/api/v1/questions/" + questionId + "/answers/" + answerId)));
            assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatus());
        });
        HttpResponse<?> deleteResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.DELETE("/api/v1/questions/" + questionId)));

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatus());
        assertEquals(count, assertDoesNotThrow(() -> client.retrieve("/api/v1/questions/count", Long.class)));
    }
}