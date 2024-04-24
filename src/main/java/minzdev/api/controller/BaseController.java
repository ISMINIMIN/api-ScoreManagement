package minzdev.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import minzdev.api.ApiResponse;
import minzdev.api.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    public <T> ApiResponse<T> makeApiResponse(List<T> results) {
        return new ApiResponse<>(results);
    }

    public <T> ApiResponse<T> makeApiResponse(T result) {
        return new ApiResponse<>(result);
    }

}
