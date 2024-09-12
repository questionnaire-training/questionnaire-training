package training.questionnaire.api;

import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@MicronautTest
class SwaggerUiTest {

    @Test
    void openApi(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        assertDoesNotThrow(() -> client.exchange("/swagger-ui/index.html"));
        assertDoesNotThrow(() -> client.exchange("/swagger-ui/res/classic.css"));
        assertDoesNotThrow(() -> client.exchange("/swagger-ui/res/favicon-32x32.png"));
        assertDoesNotThrow(() -> client.exchange("/swagger-ui/res/swagger-ui-bundle.js"));
    }
}