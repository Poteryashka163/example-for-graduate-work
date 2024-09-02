package ru.skypro.homework.exception;
/**
 * Класс для определения исключений для метода обновления пароля.
 */
public class WrongCurrentPasswordException extends RuntimeException {
    public WrongCurrentPasswordException(String message) {super(message);}
}
