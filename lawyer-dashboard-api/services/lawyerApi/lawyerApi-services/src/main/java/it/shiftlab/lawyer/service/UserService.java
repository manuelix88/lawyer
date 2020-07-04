package it.shiftlab.lawyer.service;

import it.shiftlab.lawyer.dto.UserDTO;
import it.shiftlab.lawyer.exception.clazz.UserAlreadyExistException;
import it.shiftlab.lawyer.jpa.entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDTO findUserByEmail(String userEmail);

    UsersEntity registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;

    void createPasswordResetTokenForUser(UserDetails user, String token);
}
