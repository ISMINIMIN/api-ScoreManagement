package minzdev.api.exception.restriction;

import lombok.Getter;
import minzdev.api.exception.CustomException;
import minzdev.api.exception.ErrorCode;

@Getter
public class InputRestriction {

    private static InputRestriction instance;
    private int minScore;
    private int maxScore;

    private InputRestriction() {
        minScore = 0;
        maxScore = 100;
    }

    private InputRestriction(int minScore, int maxScore) {
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public static InputRestriction getInstance() {
        if(instance == null) {
            instance = new InputRestriction();
        }

        return instance;
    }

    public void setScore(int minScore, int maxScore) {
        if(minScore == Integer.MIN_VALUE || maxScore == Integer.MAX_VALUE) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "유효하지 않은 값입니다.");
        }

        this.minScore = minScore;
        this.maxScore = maxScore;
    }

}
