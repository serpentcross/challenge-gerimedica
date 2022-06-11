package nl.gerimedica.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {
    public static Date convertStringToLocalDateTime(String stringDate) throws ParseException {
        return stringDate == null ? null : new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
    }

    public static String convertDateToString(Date date) {
        return date == null ? null : new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

}