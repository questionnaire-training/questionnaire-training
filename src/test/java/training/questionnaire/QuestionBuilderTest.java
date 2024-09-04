package training.questionnaire;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class QuestionBuilderTest {
    @Test
    void questionBuilder() {
        List<Question> questions = List.of(
                Question.builder()
                        .title("Which statement about regions and availability domains is true?")
                        .answer(Answer.builder().title("An OCI region has one or more availability domains.").correct().build())
                        .answer(Answer.builder().title("All OCI regions have a single availability domain.").incorrect().build())
                        .answer(Answer.builder().title("Fault domains provide protection against failures across regions.").incorrect().build())
                        .answer(Answer.builder().title("All OCI regions have three availability domains.").incorrect().build())
                        .build(),

                Question.builder()
                        .title("Which statement about OCI is NOT true?")
                        .answer(Answer.builder().title("Availability domains do not share infrastructure, such as power, cooling, or network, within a region.").incorrect().build())
                        .answer(Answer.builder().title("A single fault domain can be associated with multiple availability domains within a region.").correct().build())
                        .answer(Answer.builder().title("An OCI region is a localized geographic area.").incorrect().build())
                        .answer(Answer.builder().title("An availability domain is one or more data centers located within a region.").incorrect().build())
                        .build(),

                Question.builder()
                        .title("Which capability can be used to protect against failures within an OCI availability domain?")
                        .answer(Answer.builder().title("Regions.").incorrect().build())
                        .answer(Answer.builder().title("Compartments.").incorrect().build())
                        .answer(Answer.builder().title("Load Balancers.").incorrect().build())
                        .answer(Answer.builder().title("Fault Domain.").correct().build())
                        .build(),

                Question.builder()
                        .title("Which Oracle Cloud Infrastructure service is NOT intended for a multicloud solution?")
                        .answer(Answer.builder().title("Oracle Roving Edge Infrastructure").correct().build())
                        .answer(Answer.builder().title("Oracle MySQL Heatwave on AWS").incorrect().build())
                        .answer(Answer.builder().title("Oracle Database Service for Azure").incorrect().build())
                        .answer(Answer.builder().title("Oracle Interconnect for Azure").incorrect().build())
                        .build(),

                Question.builder()
                        .title("You have subscribed to an OCI region that has one availability domain. You want to deploy a highly available application with two servers and a 2-node database. How would you place the components to maintain the high availability of the application?")
                        .answer(Answer.builder().title("Place all the components in the same fault domain.").incorrect().build())
                        .answer(Answer.builder().title("Place one server and a DB node in one fault domain, and the second server and DB node in another fault domain.").correct().build())
                        .answer(Answer.builder().title("Place the servers in one fault domain and the database nodes in another fault domain.").incorrect().build())
                        .answer(Answer.builder().title("High availability is not possible as there is only one availability domain in the region.").incorrect().build())
                        .build()





















        );


        assertNotNull(questions);
    }

}
