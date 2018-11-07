package ru.chertenok.spring.hibernate.util;

public class ErrorAPI extends RuntimeException {

    public ErrorAPI(String message) {
        super(message);
    }
}
