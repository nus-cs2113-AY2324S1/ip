package neo.exception;

import neo.type.TimeErrorType;
import neo.type.TimeType;
import neo.type.TimeValueType;

/**
 * A form of throwable that indicates conditions specific to time parsing in Neo chatbot that can be
 * caught.
 */
public class NeoTimeException extends Exception {
    protected TimeType timeType;
    protected TimeValueType timeValueType;
    protected TimeErrorType timeErrorType;

    /**
     * Constructs exception with parameters to indicate the type of exception.
     *
     * @param timeType It specifies whether the exception has time included or not.
     * @param timeValueType It specifies which component of date or time has an impossible value.
     * @param timeErrorType It specifies which type of error occurred.
     */
    public NeoTimeException(TimeType timeType, TimeValueType timeValueType, TimeErrorType timeErrorType) {
        this.timeType = timeType;
        this.timeValueType = timeValueType;
        this.timeErrorType = timeErrorType;
    }

    /**
     * Prints the exception and recommended actions to the user.
     */
    public void printException() {
        switch (timeErrorType) {
        case FORMAT:
            printFormatError();
            break;
        case VALUE:
            printValueError();
            break;
        default:
            System.out.println("Unable to print exception.");
            break;
        }
    }

    private void printValueError() {
        switch (timeValueType) {
        case DAY:
            System.out.println("Please make sure the day is from 0-31.");
            break;
        case MONTH:
            System.out.println("Please make sure the month is from 0-12.");
            break;
        case HOUR:
            System.out.println("Please make sure the hours is from 0-23.");
            break;
        case MINUTE:
            System.out.println("Please make sure the minutes is from 0-59.");
            break;
        default:
            System.out.println("Unable to print exception.");
            break;
        }
    }

    private void printFormatError() {
        switch (timeType) {
        case DATE:
            System.out.println("Please make sure you have the correct format for date. (DD/MM/YYYY)");
            break;
        case DATE_AND_TIME:
            System.out.println("Please make sure you have the correct format for date and time. (DD/MM/YYYY HHmm)");
            break;
        default:
            System.out.println("Unable to print exception.");
            break;
        }
    }
}
