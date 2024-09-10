package training.questionnaire;

import io.micronaut.core.io.Writable;

import java.io.IOException;
import java.io.StringWriter;

public final class WriterUtils {
    private WriterUtils() {
    }

    public static String asString(Writable writable) throws IOException {
        StringWriter stringWriter = new StringWriter();
        writable.writeTo(stringWriter);
        return stringWriter.toString();
    }
}
