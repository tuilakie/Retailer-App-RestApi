package com.ntneik15.selflearning.retailerapp.exception.handler;

import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<BaseResponse> handleException(Exception e) {
        return new ResponseEntity<>(new BaseResponse(new Meta(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new BaseResponse(new Meta(false, e.getMessage(), HttpStatus.NOT_FOUND)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<BaseResponse> handleForbiddenException(ForbiddenException e) {
        return new ResponseEntity<>(new BaseResponse(new Meta(false, e.getMessage(), HttpStatus.FORBIDDEN)), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<BaseResponse> handleUnauthorizedException(UnauthorizedException e) {
        return new ResponseEntity<>(new BaseResponse(new Meta(false, e.getMessage(), HttpStatus.UNAUTHORIZED)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new BaseResponse(new Meta(false, e.getMessage(), HttpStatus.BAD_REQUEST)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BaseResponse> handleConflictException(ConflictException e) {
        return new ResponseEntity<>(new BaseResponse(new Meta(false, e.getMessage(), HttpStatus.CONFLICT)), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NoContentException.class})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BaseResponse> handleNoContentException(NoContentException e) {
        return new ResponseEntity<>(new BaseResponse(new Meta(false, e.getMessage(), HttpStatus.NO_CONTENT)), HttpStatus.NO_CONTENT);
    }

}

