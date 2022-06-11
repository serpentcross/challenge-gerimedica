package nl.gerimedica.demo.exceptions;

import nl.gerimedica.demo.constants.ErrorTexts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionCode400 extends RuntimeException {

    public ExceptionCode400() {
        super(ErrorTexts.ERROR_400_TEXT);
    }

}
