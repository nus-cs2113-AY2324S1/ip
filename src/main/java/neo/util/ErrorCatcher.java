package neo.util;

import neo.exception.NeoTaskException;
import neo.exception.NeoTimeException;
import neo.type.CommandType;
import neo.type.ErrorType;
import neo.type.TimeErrorType;
import neo.type.TimeType;
import neo.type.TimeValueType;

public abstract class ErrorCatcher {
    public static void catchFormatError(CommandType type, String line) throws NeoTaskException {
        switch (type) {
        case TODO:
            if (line.contains("/from") || (line.contains("/to"))) {
                throw new NeoTaskException("/from", ErrorType.MISUSE);
            }
            if (line.contains("/by")) {
                throw new NeoTaskException("/by", ErrorType.MISUSE);
            }
            break;
        case DEADLINE:
            if (line.contains("/from") || (line.contains("/to"))) {
                throw new NeoTaskException("/from", ErrorType.MISUSE);
            }
            if (!line.contains("/by")) {
                throw new NeoTaskException("/by", ErrorType.FORMAT);
            }
            break;
        case EVENT:
            if (line.contains("/by")) {
                throw new NeoTaskException("/by", ErrorType.MISUSE);
            }
            if (!line.contains("/from")) {
                throw new NeoTaskException("/from", ErrorType.FORMAT);
            }
            if (!line.contains("/to")) {
                throw new NeoTaskException("/to", ErrorType.FORMAT);
            }
            break;
        }
    }
    private static void catchDateAndTimeError(String line) throws NeoTimeException {
        String[] dateTime = line.split(" ");
        if (!line.matches("(.*)/(.*)/(.*)") || dateTime[1].length() != 4) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.DAY, TimeErrorType.FORMAT);
        }

        catchDateError(dateTime[0]);

        int hourStartIndex = 0;
        int minuteStartIndex = 2;

        String stringHour = dateTime[1].substring(hourStartIndex, minuteStartIndex);
        String stringMinute = dateTime[1].substring(minuteStartIndex);
        int hour;
        int minute;

        try {
            hour = Integer.parseInt(stringHour);
            minute = Integer.parseInt(stringMinute);
        } catch (NumberFormatException e) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.HOUR, TimeErrorType.FORMAT);
        }

        if (hour > 23) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.HOUR, TimeErrorType.VALUE);
        } else if (minute > 59) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.MINUTE, TimeErrorType.VALUE);
        }
    }

    private static void catchDateError(String line) throws NeoTimeException {
        String[] date = line.split("/");

        if (!line.matches("(.*)/(.*)/(.*)")) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.HOUR, TimeErrorType.FORMAT);
        }

        String stringDay = date[0];
        String stringMonth = date[1];
        String year = date[2];

        if (stringDay.length() != 2 || stringMonth.length() != 2 || year.length() != 4) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.MINUTE, TimeErrorType.FORMAT);
        }

        int day;
        int month;

        try {
            day = Integer.parseInt(stringDay);
            month = Integer.parseInt(stringMonth);
        } catch (NumberFormatException e) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.HOUR, TimeErrorType.FORMAT);
        }

        if (day > 31) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.DAY, TimeErrorType.VALUE);
        } else if (month > 12) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.MONTH, TimeErrorType.VALUE);
        }
    }
    private static boolean hasTime(String line) {
        String[] dateAndTime = line.split(" ");
        return dateAndTime.length == 2;
    }
    public static void catchTimeFormatError(String line) throws NeoTimeException {
        if (hasTime(line)) {
            catchDateAndTimeError(line);
        } else {
            catchDateError(line);
        }
    }

    public static void catchEmptyDescription(String field, String description) throws NeoTaskException {
        if (description.isBlank()) {
            throw new NeoTaskException(field, ErrorType.EMPTY);
        }
    }

}
