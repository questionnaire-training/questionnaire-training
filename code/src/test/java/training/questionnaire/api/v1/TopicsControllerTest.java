package training.questionnaire.api.v1;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import org.junit.jupiter.api.Test;
import training.questionnaire.repositories.jdbc.AbstractMySqlTest;
import training.questionnaire.repositories.jdbc.MicronautTestMysql;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTestMysql
class TopicsControllerTest extends AbstractMySqlTest {

    @Test
    void crud(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        long count = assertDoesNotThrow(() -> client.retrieve("/api/v1/topics/count", Long.class));
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/api/v1/topics", "{\"name\":\"Oracle Cloud Infrastructure Fundamentals\"}")));
        assertEquals(HttpStatus.CREATED, response.getStatus());
        String location = response.header(HttpHeaders.LOCATION);
        assertTrue(location.startsWith("/api/v1/topics/"));
        assertTrue(location.endsWith("/show"));
        assertEquals(count + 1 , assertDoesNotThrow(() -> client.retrieve("/api/v1/topics/count", Long.class)));
        String id = location.substring(location.indexOf("/api/v1/topics/") + "/api/v1/topics/".length(), location.indexOf("/show"));
        HttpResponse<?> deleteResponse = assertDoesNotThrow(() -> client.exchange(HttpRequest.DELETE("/api/v1/topics/" + id)));
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatus());
        assertEquals(count, assertDoesNotThrow(() -> client.retrieve("/api/v1/topics/count", Long.class)));
    }
}