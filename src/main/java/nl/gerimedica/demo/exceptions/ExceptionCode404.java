package nl.gerimedica.demo.exceptions;

import nl.gerimedica.demo.constants.ErrorTexts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionCode404 extends RuntimeException {

    public ExceptionCode404() {
        super(ErrorTexts.ERROR_404_TEXT);
    }

}
