package torchie.parser;

import torchie.exception.InvalidDateTimeException;

import java.time.LocalDateTime;
public class DateTimeParser {

    /**
     * Obtain a LocalDateTime object from a string
     *
     * @param s user input string that contains the string to be converted to LocalDateTime object
     * @return LocalDateTime object that represents the key task dates
     *
     * @throws InvalidDateTimeException the date and time of the input string is in the wrong format
     *
     */
    public LocalDateTime getDateTimeObject(String s) throws InvalidDateTimeException{
        String[] dateTimeArray = s.split("T");

        // if both date and time are present
        if (dateTimeArray.length==2) {
            return LocalDateTime.parse(dateTimeArray[0] + "T" + dateTimeArray[1]);
        } else {
            throw new InvalidDateTimeException();
        }

    }
}
