package com.quiz.exceptions;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

    public static void throwNotFoundException(String message) {
        throw new CustomException(message);
    }

    public static void throwAlreadyExistsException(String message) {
        throw new CustomException(message);
    }

    public static void throwInvalidInputException(String message) {
        throw new CustomException(message);
    }

    public static void throwFailedException(String message) {
        throw new CustomException(message);
    }

    public static void throwAlreadyInUseException(String message) {
        throw new CustomException(message);
    }
}
