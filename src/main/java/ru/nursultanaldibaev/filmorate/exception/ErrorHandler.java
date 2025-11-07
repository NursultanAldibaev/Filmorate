package ru.nursultanaldibaev.filmorate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    // Обрабатывает ошибки валидации
    @ExceptionHandler(ValidationException.class)
    public Map<String, String> handleValidationException(final ValidationException e) {
        return Map.of(
                "error", "Ошибка валидации",
                "message", e.getMessage()
        );
    }

    // Обрабатывает, если не найден пользователь или фильм
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleNotFoundException(final NotFoundException e) {
        return Map.of(
                "error", "Объект не найден",
                "message", e.getMessage()
        );
    }

    // Обрабатывает все остальные ошибки
    @ExceptionHandler
    public Map<String, String> handleException(final Throwable e) {
        return Map.of(
                "error", "Произошла ошибка",
                "message", e.getMessage()
        );
    }
}
