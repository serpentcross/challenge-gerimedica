package nl.gerimedica.demo.exceptions;

import nl.gerimedica.demo.constants.ErrorTexts;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExceptionCode500 extends RuntimeException {

    public ExceptionCode500() {
        super(ErrorTexts.ERROR_500_TEXT);
    }

}