package training.questionnaire.repositories;
import io.micronaut.core.annotation.NonNull;

@FunctionalInterface
public interface IdGenerator {

    @NonNull
    String generate();
}