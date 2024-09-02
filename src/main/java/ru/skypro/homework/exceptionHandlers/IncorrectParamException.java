package ru.skypro.homework.exceptionHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exception.*;

@ControllerAdvice
public class IncorrectParamException {

    @ExceptionHandler(AccessErrorException.class)
    public ResponseEntity<?> accessException() {
        return ResponseEntity.badRequest().body("Access error exception.");
    }

    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity<?> adNotFound(){
        return ResponseEntity.badRequest().body("Ad not found.");
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> commentNotFound(){
        return ResponseEntity.badRequest().body("Comment not found.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFound(){
        return ResponseEntity.badRequest().body("User not found.");
    }

    @ExceptionHandler(WrongCurrentPasswordException.class)
    public ResponseEntity<?> wrongCurrentPassword(){
        return ResponseEntity.badRequest().body("Wrong current password.");
    }
}