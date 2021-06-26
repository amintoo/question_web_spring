package fr.gamedev.question.web.question;

/**
 * Define contract to consume and Produce Data.
 * @author djer13
 *
 */
public interface QuestionService {

    /**
     * Get a Question to ask to a User.
     * @param userId userID : used to search for available questions.
     * @return A Question.
     */
    public Question getQuestion(String userId);

    /**
     * Validate a response provided by a User
     * @param question the Asked question and the user Response
     * @return The correctness of the question and earned points.
     */
    public AnswerStatus validateResponse(Question question);

}
