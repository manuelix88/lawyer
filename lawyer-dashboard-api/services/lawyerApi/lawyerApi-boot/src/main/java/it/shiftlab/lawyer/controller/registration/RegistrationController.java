package it.shiftlab.lawyer.controller.registration;

import it.shiftlab.lawyer.dto.UserDTO;
import it.shiftlab.lawyer.models.GenericResponseMessage;
import it.shiftlab.lawyer.service.ISecurityUserService;
import it.shiftlab.lawyer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;

@RestController
public class RegistrationController {

    private UserDetailsService userDetailsService;

    private UserService userService;

    private JavaMailSender mailSender;

    private MessageSource messages;

    private Environment env;

    private ISecurityUserService securityUserService;

    @Autowired
    public RegistrationController(UserDetailsService userDetailsService, UserService userService, JavaMailSender mailSender,
                                  MessageSource messages, Environment env, ISecurityUserService securityUserService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.mailSender = mailSender;
        this.messages = messages;
        this.env = env;
        this.securityUserService = securityUserService;
    }


    @PostMapping(value = "public/registration")
    public ResponseEntity<?> registerUserAccount(@RequestBody @Valid UserDTO userDto,
                                                 HttpServletRequest request) {
        userService.registerNewUserAccount(userDto);
        return ResponseEntity.ok(new GenericResponseMessage("ok", "Utente Registrato correttamente"));
    }

    @GetMapping(value = "public/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String userEmail, HttpServletRequest request
    ) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        if (userDetails == null) {
            throw new EntityNotFoundException("Indirizzo inesistente " + userEmail);
        }
        String url = request.getHeader("Origin");
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(userDetails, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token +";" + Base64.getEncoder().encodeToString(url.getBytes()), userDetails));
        return ResponseEntity.ok(new GenericResponseMessage("ok", "ok"));
    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, UserDetails user) {
        String url = contextPath + "/public/changePassword?token=" + token;
//        String message = messages.getMessage("message.resetPassword",
//                null, locale);
        return constructEmail("Reset Password", "Resetta la tua password " + " \r\n" + url, user);
    }

    @GetMapping("/public/changePassword")
    public ModelAndView   showChangePasswordPage(
            final Locale locale,
            final Model model,
            @RequestParam("token") final String token
    ) {
        String[] split = token.split(";");
        final String result = securityUserService.validatePasswordResetToken(split[0]);
        String urlPartial = new String(Base64.getDecoder().decode(split[1]));
        String urlWebSite = urlPartial + "/";
        if(result != null) {
            String message = messages.getMessage("auth.message." + result, null, locale);
            return new ModelAndView("redirect:" +urlWebSite+"login.html?lang=" + locale.getLanguage() + "&message=" + message)  ;
        }else {
//            model.addAttribute("token", token);
            String usernameByToken = securityUserService.getUsernameByToken(split[0]);
            return new ModelAndView("redirect:"+ urlWebSite +"reset-password?id=" +usernameByToken);
        }
    }

    @PostMapping("/public/savePassword")
    public ResponseEntity<?> savePassword(@RequestBody UserDTO userDto) {
        userService.changeUserPassword(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private SimpleMailMessage constructEmail(String subject, String body,
                                             UserDetails user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getUsername());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
