package it.shiftlab.lawyer.exception.clazz;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String username) {
        super(username);
    }
}
