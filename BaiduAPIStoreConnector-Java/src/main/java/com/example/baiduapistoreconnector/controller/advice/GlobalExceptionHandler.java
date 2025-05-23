package com.example.baiduapistoreconnector.controller.advice;

import com.example.baiduapistoreconnector.exception.BaiduApiResponseException;
import com.example.baiduapistoreconnector.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaiduApiResponseException.class)
    public ResponseEntity<ErrorResponse> handleBaiduApiResponseException(BaiduApiResponseException ex, WebRequest request) {
        logger.error("Baidu API Response Error: errNum={}, retMsg={}", ex.getErrNum(), ex.getRetMsg(), ex);
        // Consider mapping specific errNum to different HttpStatus codes if appropriate
        HttpStatus status = HttpStatus.BAD_GATEWAY; // 502 as a general error from upstream
        if (ex.getErrNum() == 300204 || ex.getErrNum() == 300205) { // Example: Invalid city
             status = HttpStatus.BAD_REQUEST; // 400 for client-side errors
        }

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "Baidu API Error: " + ex.getRetMsg() + " (errNum: " + ex.getErrNum() + ")",
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex, WebRequest request) {
        logger.error("IO Exception: {}", ex.getMessage(), ex);
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE; // 503
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "Service unavailable or network error: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        logger.error("Illegal Argument Exception: {}", ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "Invalid request parameter: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Unhandled Exception: {}", ex.getMessage(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "An unexpected error occurred: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
