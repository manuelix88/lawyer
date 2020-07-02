package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.jpa.entity.AuthoritiesEntity;
import it.shiftlab.lawyer.jpa.entity.AuthorityName;
import it.shiftlab.lawyer.jpa.entity.UsersEntity;
import it.shiftlab.lawyer.jpa.repository.AuthorityRepository;
import it.shiftlab.lawyer.jpa.repository.UserRepository;
import it.shiftlab.lawyer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

            /**
             * Inizializzo i dati del mio test
             */


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
}
