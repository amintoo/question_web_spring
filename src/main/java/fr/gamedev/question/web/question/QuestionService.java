/**
 *
 */
package fr.gamedev.question.web.question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Mock Question Service.
 * @author djer13
 *
 */
@Service
public class QuestionService {

    private static final String QUESTION_1 = "Java est-il un bon language";
    private static final String QUESTION_1_EXEPCTED_ANSWER = "Oui";

    private static final String QUESTION_2 = "Php est-il un bon language";
    private static final String QUESTION_2_EXEPCTED_ANSWER = "Oui";

    private static final String QUESTION_3 = "1024 est la réponse à : La grande question sur la vie, l'univers et le reste";
    private static final String QUESTION_3_EXEPCTED_ANSWER = "Non";

    public Question getQuestion(String userId) {

        Question theQuestion = new Question();

        if ("Marvin".equals(userId)) {
            theQuestion.setQuestion(QUESTION_3);
        } else if ("Jérome".equals(userId)) {
            theQuestion.setQuestion(QUESTION_1);
        } else {
            theQuestion.setQuestion(QUESTION_2);
        }

        List<String> possibleAnswers = new ArrayList<String>();
        possibleAnswers.add("Oui");
        possibleAnswers.add("Non");
        theQuestion.setPossibleAnswers(possibleAnswers);

        return theQuestion;

    }

    public AnswerStatus validateResponse(Question question) {
        AnswerStatus answer = new AnswerStatus();
        Boolean isCorrect = Boolean.FALSE;
        answer.setEarnedPoint(0);

        if (QUESTION_1.equals(question.getQuestion())) {
            isCorrect = QUESTION_1_EXEPCTED_ANSWER.equals(question.getUserAnswer());
            if (isCorrect) {
                answer.setEarnedPoint(11);
            }
        } else if (QUESTION_2.equals(question.getQuestion())) {
            isCorrect = QUESTION_2_EXEPCTED_ANSWER.equals(question.getUserAnswer());
            if (isCorrect) {
                answer.setEarnedPoint(10);
            }
        } else if (QUESTION_3.equals(question.getQuestion())) {
            isCorrect = QUESTION_3_EXEPCTED_ANSWER.equals(question.getUserAnswer());
            if (isCorrect) {
                answer.setEarnedPoint(42);
            }
        } else {
            isCorrect = Boolean.FALSE;
        }

        answer.setCorrect(isCorrect);

        return answer;
    }

}
