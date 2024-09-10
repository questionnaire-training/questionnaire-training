package training.questionnaire;

import org.junit.jupiter.api.Test;
import training.questionnaire.repositories.IdGenerator;
import training.questionnaire.repositories.KsuidGenerator;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class UnderscoreQuestionRenderTest {
    @Test
    void questionBuilder() {
        IdGenerator idGenerator = new KsuidGenerator();
        List<Question> questions = List.of(
                Question.builder()
                        .id(idGenerator.generate())
                        .name("Which statement about regions and availability domains is true?")
                        .answer(Answer.builder().id(idGenerator.generate()).name("An OCI region has one or more availability domains.").correct().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("All OCI regions have a single availability domain.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Fault domains provide protection against failures across regions.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("All OCI regions have three availability domains.").incorrect().build())
                        .build(),

                Question.builder()
                        .id(idGenerator.generate())
                        .name("Which statement about OCI is NOT true?")
                        .answer(Answer.builder().id(idGenerator.generate()).name("Availability domains do not share infrastructure, such as power, cooling, or network, within a region.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("A single fault domain can be associated with multiple availability domains within a region.").correct().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("An OCI region is a localized geographic area.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("An availability domain is one or more data centers located within a region.").incorrect().build())
                        .build(),

                Question.builder()
                        .id(idGenerator.generate())
                        .name("Which capability can be used to protect against failures within an OCI availability domain?")
                        .answer(Answer.builder().id(idGenerator.generate()).name("Regions.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Compartments.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Load Balancers.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Fault Domain.").correct().build())
                        .build(),

                Question.builder()
                        .id(idGenerator.generate())
                        .name("Which Oracle Cloud Infrastructure service is NOT intended for a multicloud solution?")
                        .answer(Answer.builder().id(idGenerator.generate()).name("Oracle Roving Edge Infrastructure").correct().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Oracle MySQL Heatwave on AWS").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Oracle Database Service for Azure").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Oracle Interconnect for Azure").incorrect().build())
                        .build(),

                Question.builder()
                        .id(idGenerator.generate())
                        .name("You have subscribed to an OCI region that has one availability domain. You want to deploy a highly available application with two servers and a 2-node database. How would you place the components to maintain the high availability of the application?")
                        .answer(Answer.builder().id(idGenerator.generate()).name("Place all the components in the same fault domain.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Place one server and a DB node in one fault domain, and the second server and DB node in another fault domain.").correct().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("Place the servers in one fault domain and the database nodes in another fault domain.").incorrect().build())
                        .answer(Answer.builder().id(idGenerator.generate()).name("High availability is not possible as there is only one availability domain in the region.").incorrect().build())
                        .build()





















        );


        assertNotNull(questions);
    }

}
