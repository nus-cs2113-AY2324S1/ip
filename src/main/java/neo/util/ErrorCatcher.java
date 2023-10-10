package neo.util;

import neo.exception.NeoTaskException;
import neo.exception.NeoTimeException;
import neo.type.CommandType;
import neo.type.ErrorType;
import neo.type.TimeErrorType;
import neo.type.TimeType;
import neo.type.TimeValueType;

/**
 * Represents the methods related to catching input errors. This class is abstract as its
 * main purpose is to provide methods to catch errors.
 */
public abstract class ErrorCatcher {
    /**
     * Catches errors when user inputs the wrong format while trying to add tasks to the list.
     *
     * @param type This is the type of task that the user intends to add.
     * @param line This is the user input line.
     * @throws NeoTaskException If wrong format is detected.
     */
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
        default:
            System.out.println("Unable to catch format error.");
            break;
        }
    }

    /**
     * Catches errors when a line in data.txt file does not match the required parameters
     * for the task it is representing.
     *
     * @param type This is the type of task that the user intends to add.
     * @param arraySize This is the number of parameters in the line from data.txt.
     * @throws NeoTaskException if the number of parameters from the line in data.txt and the required
     *                          parameters for its task type do not match.
     */
    public static void catchReadFormatError(CommandType type, int arraySize) throws NeoTaskException {
        int todoArraySize = 3;
        int deadlineArraySize = 4;
        int eventArraySize = 5;

        switch (type) {
        case TODO:
            if (arraySize != todoArraySize) {
                throw new NeoTaskException();
            }
            break;
        case DEADLINE:
            if (arraySize != deadlineArraySize) {
                throw new NeoTaskException();
            }
            break;
        case EVENT:
            if (arraySize != eventArraySize) {
                throw new NeoTaskException();
            }
            break;
        default:
            System.out.println("Unable to catch format error.");
            break;
        }
    }

    /**
     * Catches errors when the integer representing whether a task is marked is not 1 or 0.
     *
     * @param mark This is the integer representing if a task is marked.
     * @throws NeoTaskException if mark is not 1 or 0.
     */
    public static void catchReadMarkError(int mark) throws NeoTaskException {
        if (mark != 0 && mark != 1) {
            throw new NeoTaskException();
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

    /**
     * Catches errors when user inputs the date or date and time in a wrong format.
     *
     * @param line This is the string representing the date or date and time.
     * @throws NeoTimeException If errors are detected in the format of the date or date and time.
     */
    public static void catchTimeFormatError(String line) throws NeoTimeException {
        if (hasTime(line)) {
            catchDateAndTimeError(line);
        } else {
            catchDateError(line);
        }
    }

    /**
     * Catches errors when the description of task or its dates are empty.
     *
     * @param field This is the part of the task. E.g.: /by, /from, /to
     * @param description This is the description of the task.
     * @throws NeoTaskException If empty fields are detected.
     */
    public static void catchEmptyDescription(String field, String description) throws NeoTaskException {
        if (description.isBlank()) {
            throw new NeoTaskException(field, ErrorType.EMPTY);
        }
    }
}
