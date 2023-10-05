package torchie.parser;

import torchie.exception.InvalidDateTimeException;

import java.time.LocalDateTime;
public class DateTimeParser {
    private String deadlineString;

    public DateTimeParser(String ds) {
        this.deadlineString = ds;
    }

    //2007-12-03 1015
    public LocalDateTime getDateTimeObject(String s) throws InvalidDateTimeException{
        String[] dateTimeArray = s.split("T");

        // if both date and time are present
        if (dateTimeArray.length==2) {
            return LocalDateTime.parse(dateTimeArray[0] + "T" + dateTimeArray[1]);
        } /*else if (dateTimeArray.length==1) {
            // if only date is present

            // if only time is present
        }*/ else {
            throw new InvalidDateTimeException();
        }

    }
}
