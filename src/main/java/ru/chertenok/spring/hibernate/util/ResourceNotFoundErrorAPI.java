package ru.chertenok.spring.hibernate.util;

public class ResourceNotFoundErrorAPI extends RuntimeException {

    public ResourceNotFoundErrorAPI(String message) {
        super(message);
    }
}
