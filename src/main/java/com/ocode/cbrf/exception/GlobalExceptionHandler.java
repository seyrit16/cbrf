package com.ocode.cbrf.exception;

import com.ocode.cbrf.dto.ResultDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultDTO<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ex.printStackTrace();
        ResultDTO<?> resultErr = ResultDTO.NOT_FOUND_RESULT;
        resultErr.setErrorMessage(ex.getMessage());
        return resultErr;
    }

    @ExceptionHandler(ConflictDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResultDTO<?> handleConflictDataException(Exception ex){
        ResultDTO<?> resultErr = new ResultDTO<>("false",409L,null,"409", ex.getMessage());
        return resultErr;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultDTO<?> handleNoResourceFoundException(Exception ex){
        ResultDTO<?> resultErr = new ResultDTO<>("false",403L,null,"403", "Forbidden");
        return resultErr;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultDTO<?> handleException(Exception ex){
        ResultDTO<?> resultErr = ResultDTO.INTERNAL_SERVER_RESULT;
        resultErr.setErrorMessage("internal server error");
        return resultErr;
    }

}
