package neo.exception;

import neo.type.TimeErrorType;
import neo.type.TimeType;
import neo.type.TimeValueType;
public class NeoTimeException extends Exception {
    protected TimeType timeType;
    protected TimeValueType timeValueType;
    protected TimeErrorType timeErrorType;

    public NeoTimeException(TimeType timeType, TimeValueType timeValueType, TimeErrorType timeErrorType) {
        this.timeType = timeType;
        this.timeValueType = timeValueType;
        this.timeErrorType = timeErrorType;
    }

    public void printException() {
        switch (timeErrorType) {
        case FORMAT:
            printFormatError();
            break;
        case VALUE:
            printValueError();
            break;
        default:
            System.out.println("Sorry. I do not understand what timings you have put.");
            break;
        }
    }

    private void printValueError() {
        switch (timeValueType) {
        case DAY:
            System.out.println("Please make sure the day is from 0-31.");
            break;
        case MONTH:
            System.out.println("Please make sure the month is from 0-31.");
            break;
        case HOUR:
            System.out.println("Please make sure the hours is from 0-23.");
            break;
        case MINUTE:
            System.out.println("Please make sure the minutes is from 0-59.");
        }
    }

    private void printFormatError() {
        switch (timeType) {
        case DATE:
            System.out.println("Please make sure you have the correct format for date. (DD/MM/YYYY)");
            break;
        case DATE_AND_TIME:
            System.out.println("Please make sure you have the correct format for date and time. (DD/MM/YYYY HHmm)");
        }
    }
}
