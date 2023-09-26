package com.example.demo.errors.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.NoSuchElementException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//    @ExceptionHandler({NoSuchElementException.class})
//    private String notFound(Model model, HttpServletRequest request) {
//       model.addAttribute("status",HttpStatus.NOT_FOUND.value());
//       model.addAttribute("reason",HttpStatus.NOT_FOUND.getReasonPhrase());
//       model.addAttribute("details",request);
//       return "errors/error";
//    }

    @ExceptionHandler(DataAccessException.class)
    private ErrorResponse handleDatabaseException(DataAccessException exception) {
        log.error("Database Exception: ", exception);
        return ErrorResponse.builder(exception, HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred").build();
    }

    @ExceptionHandler(Exception.class)
    private ErrorResponse handleGeneralException(Exception exception) {
        log.error("Unhandled Exception: ", exception);
        return ErrorResponse.builder(exception, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred").build();
    }

}
