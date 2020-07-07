package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.dto.UserDTO;
import it.shiftlab.lawyer.exception.clazz.UserAlreadyExistException;
import it.shiftlab.lawyer.exception.clazz.UserNotFound;
import it.shiftlab.lawyer.jpa.entity.AuthoritiesEntity;
import it.shiftlab.lawyer.jpa.entity.AuthorityName;
import it.shiftlab.lawyer.jpa.entity.PasswordResetTokenEntity;
import it.shiftlab.lawyer.jpa.entity.UsersEntity;
import it.shiftlab.lawyer.jpa.repository.AuthorityRepository;
import it.shiftlab.lawyer.jpa.repository.PasswordTokenRepository;
import it.shiftlab.lawyer.jpa.repository.UserRepository;
import it.shiftlab.lawyer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UsersEntity registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }

//        AuthoritiesEntity authorityAdmin = new AuthoritiesEntity();
//        authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
//        authorityAdmin = authorityRepository.save(authorityAdmin);

        AuthoritiesEntity authorityUser = new AuthoritiesEntity();
        authorityUser.setName(AuthorityName.ROLE_USER);
        authorityUser = authorityRepository.save(authorityUser);


        List<AuthoritiesEntity> authorities = Arrays.asList(new AuthoritiesEntity[]{authorityUser});

        UsersEntity user = new UsersEntity();
        user.setAuthorities(authorities);
        user.setEnabled(true);
        user.setUsername(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void createPasswordResetTokenForUser(UserDetails user, String token) {
        UsersEntity userEntiy = userRepository.findByUsername(user.getUsername());
        PasswordResetTokenEntity myToken = new PasswordResetTokenEntity(token, userEntiy);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public void changeUserPassword(UserDTO userDto) {
        UsersEntity byUsername = userRepository.findByUsername(userDto.getEmail());
        if (byUsername == null) {
            throw new UserNotFound("Questo utente non esiste: " + userDto.getEmail());
        }
        byUsername.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(byUsername);
    }

    @Override
    public UserDTO findUserByEmail(String userEmail) {
        UsersEntity byUsername = userRepository.findByUsername(userEmail);
        if (byUsername != null) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userEmail);
        }

        return null;
    }

    private boolean emailExists(String email) {
        return userRepository.findByUsername(email) != null;
    }
}
