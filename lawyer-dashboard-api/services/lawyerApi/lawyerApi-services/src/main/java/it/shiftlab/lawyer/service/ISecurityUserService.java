package it.shiftlab.lawyer.service;

public interface ISecurityUserService {
    String validatePasswordResetToken(String token);

    String getUsernameByToken(String token);
}
