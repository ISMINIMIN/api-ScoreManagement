package minzdev.api.exception;

import lombok.Getter;
import org.springframework.util.StringUtils;

public class CustomException extends RuntimeException {

    @Getter private final ErrorCode errorCode;
    private String message;
    @Getter private Object inputRestriction;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public CustomException(ErrorCode errorCode, String message, Object inputRestriction) {
        this.errorCode = errorCode;
        this.message = message;
        this.inputRestriction = inputRestriction;
    }

    public String getMessage() {
        if(StringUtils.hasLength(message)) {
            return message;
        }

        return errorCode.getMessage();
    }

}
