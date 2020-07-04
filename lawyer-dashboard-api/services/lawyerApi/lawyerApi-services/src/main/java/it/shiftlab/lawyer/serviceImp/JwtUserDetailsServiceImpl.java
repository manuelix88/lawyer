package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.jpa.entity.UsersEntity;
import it.shiftlab.lawyer.jpa.repository.UserRepository;
import it.shiftlab.lawyer.mapper.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UsersEntity user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        } else {
            return JwtUserFactory.createFromEntityToDTO(user);
        }
    }
}
