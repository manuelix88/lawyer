package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.jpa.entity.PasswordResetTokenEntity;
import it.shiftlab.lawyer.jpa.repository.PasswordTokenRepository;
import it.shiftlab.lawyer.service.ISecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@Transactional
public class ISecurityUserServiceImpl implements ISecurityUserService {

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Override
    public String validatePasswordResetToken(String token) {
        final PasswordResetTokenEntity passToken = passwordTokenRepository.findByToken(token);
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetTokenEntity passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetTokenEntity passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
