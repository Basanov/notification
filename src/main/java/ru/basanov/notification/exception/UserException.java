package ru.basanov.notification.exception;

public class UserException extends RuntimeException {

    public UserException(Long id) {
        super("Не найден пользователь с указанным идентификатором " + id);
    }
}
