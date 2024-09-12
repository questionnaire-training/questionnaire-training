package training.questionnaire.api.v1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Questionnaire Training",
                version = "1.0",
                description = "Questionnaire Training  API",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://sergiodelamo.com", name = "Sergio del Amo", email = "sergio.delamo@softamo.com")
        )
)
public final class Api {
    private Api() {
    }

    public static final String SLASH = "/";
    public static final String API = "api";
    public static final String V1 = "v1";
    public static final String ACTION_SHOW = "show";
    public static final String ACTION_SAVE = "save";
    public static final String ACTION_COUNT = "count";
    public static final String PATH_VARIABLE_ID = "{id}";
}
