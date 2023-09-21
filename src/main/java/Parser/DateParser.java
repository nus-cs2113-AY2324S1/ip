package Parser;

import Exceptions.CSGPTDateParsingException;
import Exceptions.CSGPTParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateParser {
    public static LocalDate parse(String dateString) throws CSGPTDateParsingException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new CSGPTDateParsingException();
        }
    }
}
