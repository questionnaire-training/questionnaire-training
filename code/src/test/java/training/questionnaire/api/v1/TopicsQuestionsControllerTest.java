package training.questionnaire.api.v1;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import org.junit.jupiter.api.Test;
import training.questionnaire.repositories.TopicQuestionSave;
import training.questionnaire.repositories.jdbc.AbstractMySqlTest;
import training.questionnaire.repositories.jdbc.MicronautTestMysql;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTestMysql
class TopicsQuestionsControllerTest extends AbstractMySqlTest {

    @Test
    void crud(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        long topicsCount = assertDoesNotThrow(() -> client.retrieve("/api/v1/topics/count", Long.class));
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/topics", "{\"name\":\"Oracle Cloud Infrastructure Fundamentals\"}")));
        assertEquals(HttpStatus.CREATED, response.getStatus());
        String location = response.header(HttpHeaders.LOCATION);
        assertTrue(location.startsWith("/api/v1/topics/"));
        assertTrue(location.endsWith("/show"));
        assertEquals(topicsCount + 1 , assertDoesNotThrow(() -> client.retrieve("/api/v1/topics/count", Long.class)));
        String topicId = location.substring(location.indexOf("/api/v1/topics/") + "/api/v1/topics/".length(), location.indexOf("/show"));

        long questionsCount = assertDoesNotThrow(() -> client.retrieve("/api/v1/questions/count", Long.class));
        HttpResponse<?> saveQuestionResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/questions", "{\"name\":\"Which statement about OCI compartments is NOT true?\"}")));
        assertEquals(HttpStatus.CREATED, saveQuestionResponse.getStatus());
        location = saveQuestionResponse.header(HttpHeaders.LOCATION);
        assertTrue(location.startsWith("/api/v1/questions/"));
        assertTrue(location.endsWith("/show"));
        assertEquals(questionsCount + 1 , assertDoesNotThrow(() -> client.retrieve("/api/v1/questions/count", Long.class)));
        String questionId = location.substring(location.indexOf("/api/v1/questions/") + "/api/v1/questions/".length(), location.indexOf("/show"));

        HttpResponse<?> topicQuestionResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/topics/questions", new TopicQuestionSave( topicId,  questionId))));
        assertEquals(HttpStatus.NO_CONTENT, topicQuestionResponse.getStatus());

        HttpResponse<?> deleteQuestionResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.DELETE("/api/v1/questions/" + questionId)));
        assertEquals(HttpStatus.NO_CONTENT, deleteQuestionResponse.getStatus());
        assertEquals(questionsCount, assertDoesNotThrow(() -> client.retrieve("/api/v1/questions/count", Long.class)));

        HttpResponse<?> deleteTopicResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.DELETE("/api/v1/topics/" +topicId)));
        assertEquals(HttpStatus.NO_CONTENT, deleteTopicResponse.getStatus());
        assertEquals(topicsCount, assertDoesNotThrow(() -> client.retrieve("/api/v1/topics/count", Long.class)));
    }
}