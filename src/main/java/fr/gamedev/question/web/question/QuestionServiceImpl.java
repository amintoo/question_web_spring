package fr.gamedev.question.web.question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import fr.gamedev.question.web.question.external.AskedQuestion;

/**
 * Adapter to communicate with the backEnd for questions.
 * Form more info about Rest Template : https://spring.io/guides/gs/consuming-rest/
 * @author djer13
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private RestTemplate questionRestTemplate;
    //http://localhost:8080/userAnswer/search/findFirstByUserLoginAndPointsIsNull?userLogin=test0&projection=askedQuestion
    private static final String USER_GET_ONE_QUESTION_URL = "http://localhost:8080/userAnswer/search/findFirstByUserLoginAndPointsIsNull";
    //POST http://localhost:8080/response/512/true
    private static final String USER_PROVIDE_ANSWER_URL = "http://localhost:8080/response";

    public QuestionServiceImpl(@Autowired RestTemplate theQuestionRestTemplate) {
        this.questionRestTemplate = theQuestionRestTemplate;

    }

    public Question getQuestion(String userId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(USER_GET_ONE_QUESTION_URL)
                .queryParam("userLogin", userId).queryParam("projection", "askedQuestion");

        AskedQuestion askedQuestion = questionRestTemplate.getForObject(builder.toUriString(), AskedQuestion.class);
        Question question = new Question();

        question.setQuestion(askedQuestion.getQuestion().getContent());
        question.setQuestionId(askedQuestion.getId());

        List<String> possibleAnswers = new ArrayList<String>();
        possibleAnswers.add("Oui");
        possibleAnswers.add("Non");
        question.setPossibleAnswers(possibleAnswers);

        return question;
    }

    public AnswerStatus validateResponse(Question question) {

        Boolean userProposition = Boolean.FALSE;

        if (null != question.getUserAnswer() && "Oui".equals(question.getUserAnswer())) {
            userProposition = Boolean.TRUE;
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(USER_PROVIDE_ANSWER_URL)
                .queryParam("userAnswerId", question.getQuestionId()).queryParam("answer", userProposition);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<AskedQuestion> askedQuestionResponse = questionRestTemplate.postForEntity(builder.toUriString(),
                requestEntity, AskedQuestion.class);

        AskedQuestion askedQuestion = askedQuestionResponse.getBody();
        AnswerStatus answerStatus = new AnswerStatus();
        answerStatus.setCorrect(askedQuestion.getPoints() > 0);
        answerStatus.setEarnedPoint(askedQuestion.getPoints());
        return answerStatus;
    }

}
