package fr.gamedev.question.web.question;

/**
 * @author djer13
 *
 */
public class AnswerStatus {

    private Boolean correct;

    private Integer earnedPoint;

    /**
     * @return the correct
     */
    public Boolean isCorrect() {
        return correct;
    }

    /**
     * @param correct the correct to set
     */
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    /**
     * @return the earnedPoint
     */
    public Integer getEarnedPoint() {
        return earnedPoint;
    }

    /**
     * @param earnedPoint the earnedPoint to set
     */
    public void setEarnedPoint(Integer earnedPoint) {
        this.earnedPoint = earnedPoint;
    }

}
