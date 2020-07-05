package it.shiftlab.lawyer.config;

import it.shiftlab.lawyer.exception.clazz.AnagraficaAlreadyExistException;
import it.shiftlab.lawyer.exception.clazz.ElementNotFound;
import it.shiftlab.lawyer.exception.clazz.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserAlreadyExistException.class, AnagraficaAlreadyExistException.class})
    public void springHandleExist(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler({ElementNotFound.class})
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
