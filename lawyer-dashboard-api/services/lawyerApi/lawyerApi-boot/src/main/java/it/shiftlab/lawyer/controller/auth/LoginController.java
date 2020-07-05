package it.shiftlab.lawyer.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.shiftlab.lawyer.models.JwtAuthenticationResponse;
import it.shiftlab.lawyer.security.JwtAuthenticationRequest;
import it.shiftlab.lawyer.service.UserService;
import it.shiftlab.lawyer.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messages;

    @Autowired
    private Environment env;


    @PostMapping(value = "public/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device, HttpServletResponse response) throws AuthenticationException, JsonProcessingException {

        // Effettuo l autenticazione
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Genero Token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        response.setHeader(tokenHeader,token);
        // Ritorno il token
        return ResponseEntity.ok(new JwtAuthenticationResponse(userDetails.getUsername(),userDetails.getAuthorities(), token));
    }
//
//    @PostMapping(value = "public/registration")
//    public ResponseEntity<?> registerUserAccount(@RequestBody @Valid UserDTO userDto,
//                                                 HttpServletRequest request) {
//        userService.registerNewUserAccount(userDto);
//        return ResponseEntity.ok(new GenericResponseMessage("ok", "Utente Registrato correttamente"));
//    }
//
//    @GetMapping(value = "public/resetPassword")
//    public ResponseEntity<?> resetPassword(@RequestParam("email") String userEmail, HttpServletRequest request
//                                           ) {
//       final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//        if (userDetails == null) {
////            throw new Exception();
//        }
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(userDetails, token);
//        mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, userDetails));
//        return ResponseEntity.ok(new GenericResponseMessage("ok", "ok"));
//    }
//    private SimpleMailMessage constructResetTokenEmail(
//            String contextPath, Locale locale, String token, UserDetails user) {
//        String url = contextPath + "/user/changePassword?token=" + token;
////        String message = messages.getMessage("message.resetPassword",
////                null, locale);
//        return constructEmail("Reset Password", "Resetta la tua password " + " \r\n" + url, user);
//    }
//
//    @GetMapping("/user/changePassword")
//    public String showChangePasswordPage(
//            final Locale locale,
//            final Model model,
//            @RequestParam("token") final String token
//    ) {
//        return null;
//    }
//
//    private SimpleMailMessage constructEmail(String subject, String body,
//                                             UserDetails user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getUsername());
//        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }
//
//    private String getAppUrl(HttpServletRequest request) {
//        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//    }
}
