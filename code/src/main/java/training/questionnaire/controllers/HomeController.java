package training.questionnaire.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.View;
import io.micronaut.views.fields.FormGenerator;
import io.swagger.v3.oas.annotations.Hidden;
import training.questionnaire.Answer;
import training.questionnaire.Question;
import training.questionnaire.api.v1.Api;
import training.questionnaire.exceptions.QuestionNotFoundException;
import training.questionnaire.repositories.IdGenerator;
import training.questionnaire.repositories.QuestionSave;
import training.questionnaire.repositories.QuestionsRepository;

import static training.questionnaire.api.v1.Api.*;

import java.util.Map;

@Controller
class HomeController {

    private static final String DOMAIN_QUESTIONS = "questions";
    private static final String PATH_QUESTION_SHOW= SLASH + DOMAIN_QUESTIONS + Api.SLASH  + "{questionId}" + Api.SLASH + Api.ACTION_SHOW;
    public static final String MODEL_KEY_QUESTION = "question";
    private final FormGenerator formGenerator;
    private final IdGenerator idGenerator;
    private final QuestionsRepository questionsRepository;

    HomeController(FormGenerator formGenerator, IdGenerator idGenerator, QuestionsRepository questionsRepository) {
        this.formGenerator = formGenerator;
        this.idGenerator = idGenerator;
        this.questionsRepository = questionsRepository;
    }

    @Hidden
    @Produces(MediaType.TEXT_HTML)
    @Get(PATH_QUESTION_SHOW)
    @View("question/show.html")
    Map<String, Object> questionsShow(@PathVariable String questionId) {

        Question question = Question.builder()
                .id(idGenerator.generate())
                .name("You need to stream media content from a large number of devices to AWS and then runs analytics, machine learning (ML), playback, and other processing. Which AWS service should you use?")
                .answer(Answer.builder().id(idGenerator.generate()).name("Amazon Elastic Transcoder").incorrect().explanation("Elastic Transcoder allows businesses and developers to convert (or “transcode”) media files from their original source format into versions that are optimized for various devices, such as smartphones, tablets, and PCs.").build())
                .answer(Answer.builder().id(idGenerator.generate()).name("Amazon Elastic Kinesis").incorrect().build())
                .answer(Answer.builder().id(idGenerator.generate()).name("Amazon Kinesis Video Streams").correct().explanation("Kinesis Video Streams streams media content from a large number of devices to AWS and then runs analytics, machine learning (ML), playback, and other processing. Consider using Kinesis Video Streams.").build())
                .answer(Answer.builder().id(idGenerator.generate()).name("Amazon Kinesis Transcoder").incorrect().build())
                .build();

//        Question question = Question.builder()
//                .id(idGenerator.generate())
//                .name("Which statement about regions and availability domains is true?")
//                .answer(Answer.builder().id(idGenerator.generate()).name("An OCI region has one or more availability domains.").correct().build())
//                .answer(Answer.builder().id(idGenerator.generate()).name("All OCI regions have a single availability domain.").incorrect().build())
//                .answer(Answer.builder().id(idGenerator.generate()).name("Fault domains provide protection against failures across regions.").incorrect().build())
//                .answer(Answer.builder().id(idGenerator.generate()).name("All OCI regions have three availability domains.").incorrect().build())
//                .build();

        return Map.of(MODEL_KEY_QUESTION,
                question
                //questionsRepository.get(questionId).orElseThrow(QuestionNotFoundException::new)
        );
    }
}
