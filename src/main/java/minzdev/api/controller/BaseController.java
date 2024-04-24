package minzdev.api.controller;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletResponse;
import minzdev.api.ApiResponse;
import minzdev.api.exception.CustomException;
import minzdev.api.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

abstract public class BaseController {

    @ExceptionHandler(CustomException.class)
    public <T> ApiResponse<T> customExceptionHandler(HttpServletResponse response, CustomException exception) {
        response.setStatus(exception.getErrorCode().getHttpStatus().value());
        return new ApiResponse<>(
                exception.getErrorCode().getCode(),
                exception.getMessage(),
                exception.getInputRestriction());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> ApiResponse<T> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        if (exception.getCause() instanceof MismatchedInputException mismatchedInputException) {
            return new ApiResponse<>(
                    ErrorCode.BAD_REQUEST.getCode(),
                    mismatchedInputException.getPath().get(0).getFieldName() + " 필드의 값이 잘못되었습니다.");
        }

        return new ApiResponse<>(
                ErrorCode.BAD_REQUEST.getCode(),
                "유효하지 않는 값입니다.");
    }

    public <T> ApiResponse<T> makeApiResponse(List<T> results) {
        return new ApiResponse<>(results);
    }

    public <T> ApiResponse<T> makeApiResponse(T result) {
        return new ApiResponse<>(result);
    }

}
