package it.shiftlab.lawyer.exception.clazz;

public class UserAlreadyExistException extends DataAlreadyExistException{


    public UserAlreadyExistException(String username) {
        super(username);
    }
}
