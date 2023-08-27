package ru.basanov.notification.exception;

public class EventException extends RuntimeException {

    public EventException(Long id) {
        super("Не найден событие с указанным идентификатором " + id);
    }
}
