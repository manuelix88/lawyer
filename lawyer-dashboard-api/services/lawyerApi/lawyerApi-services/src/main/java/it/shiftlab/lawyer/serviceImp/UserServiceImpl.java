package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.dto.UserDTO;
import it.shiftlab.lawyer.exception.clazz.UserAlreadyExistException;
import it.shiftlab.lawyer.jpa.entity.AuthoritiesEntity;
import it.shiftlab.lawyer.jpa.entity.AuthorityName;
import it.shiftlab.lawyer.jpa.entity.UsersEntity;
import it.shiftlab.lawyer.jpa.repository.AuthorityRepository;
import it.shiftlab.lawyer.jpa.repository.UserRepository;
import it.shiftlab.lawyer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public void addFakeData() {
        UsersEntity user = userRepository.findByUsername("pippo");
//
        if (user == null) {

            AuthoritiesEntity authorityAdmin = new AuthoritiesEntity();
            authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
            authorityAdmin = authorityRepository.save(authorityAdmin);

            AuthoritiesEntity authorityUser = new AuthoritiesEntity();
            authorityUser.setName(AuthorityName.ROLE_USER);
            authorityUser = authorityRepository.save(authorityUser);


            List<AuthoritiesEntity> authorities = Arrays.asList(new AuthoritiesEntity[]{authorityAdmin, authorityUser});


            user = new UsersEntity();
            user.setAuthorities(authorities);
            user.setEnabled(true);
            user.setUsername("pippo");
            user.setPassword(passwordEncoder.encode("admin"));

            user = userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public UsersEntity registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }

        AuthoritiesEntity authorityAdmin = new AuthoritiesEntity();
        authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
        authorityAdmin = authorityRepository.save(authorityAdmin);

        AuthoritiesEntity authorityUser = new AuthoritiesEntity();
        authorityUser.setName(AuthorityName.ROLE_USER);
        authorityUser = authorityRepository.save(authorityUser);


        List<AuthoritiesEntity> authorities = Arrays.asList(new AuthoritiesEntity[]{authorityAdmin, authorityUser});

        UsersEntity user = new UsersEntity();
        user.setAuthorities(authorities);
        user.setEnabled(true);
        user.setUsername(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
    private boolean emailExists(String email) {
        return userRepository.findByUsername(email) != null;
    }
}
