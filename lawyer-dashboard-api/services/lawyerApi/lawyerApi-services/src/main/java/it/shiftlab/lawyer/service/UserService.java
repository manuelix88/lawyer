package it.shiftlab.lawyer.service;

import it.shiftlab.lawyer.dto.UserDTO;
import it.shiftlab.lawyer.exception.clazz.UserAlreadyExistException;
import it.shiftlab.lawyer.jpa.entity.UsersEntity;

public interface UserService {
    void addFakeData();
    UsersEntity registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;
}
