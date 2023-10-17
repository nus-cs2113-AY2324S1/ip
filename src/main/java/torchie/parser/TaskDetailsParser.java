package torchie.parser;

import torchie.exception.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDetailsParser {

    private DateTimeParser dateTimeParser;
    public TaskDetailsParser() {
        dateTimeParser = new DateTimeParser();
    }

    /**
     * Get index of task to perform command on the task
     *
     * @param s user input string that contains the index of task we want to perform a command to
     * @param length size of our task list
     * @return String index of task
     *
     * @throws InvalidIndexException index out of bounds
     *
     */
    public String getIndex(String s, int length) throws InvalidIndexException {
        // split sentence into 2 parts, first word and everything else
        String[] words = s.split(" ", 2);

        if (words.length<2) {
            throw new InvalidIndexException();
        }

        // making sure content stops before the key characters such as /
        String index = words[1];

        if (index.indexOf('/') != -1) {
            int keyWordIndex = index.indexOf('/');
            index = index.substring(0, keyWordIndex - 1);
        } else if (Integer.parseInt(index) < 1 || Integer.parseInt(index) > length) {
            // index is negative or out of bounds
            throw new InvalidIndexException();
        }

        return index;
    }

    /**
     * Get task name from user input so that command can be performed
     *
     * @param s user input string that contains the name of task as a required task detail
     * @return String name of task
     *
     * @throws MissingTaskNameException name of task is missing
     *
     */
    public String getContent(String s) throws MissingTaskNameException {
        // split sentence into 2 parts, first word and everything else
        String[] words = s.split(" ", 2);

        if (words.length<2) {
            throw new MissingTaskNameException();
        }

        // making sure content stops before the key characters such as /
        String content = words[1];

        if (content.indexOf('/') != -1) {
            int keyWordIndex = content.indexOf('/');
            content = content.substring(0, keyWordIndex - 1);
        }

        return content;
    }

    /**
     * Get deadline as a string from user input and convert to LocalDateTime object
     *
     * @param s user input string that contains the deadline of task
     * @return LocalDateTime deadline of task
     *
     * @throws InvalidDeadlineFormatException format of deadline is wrong, example: deadline <name> /by DateTime
     * @throws InvalidDateTimeException the date and time of the input string is in
     *
     */
    public LocalDateTime getDeadlineDate(String s) throws InvalidDateTimeException, InvalidDeadlineFormatException {
        int SIZE_OF_BUFFER = 4;
        int keyWordIndex = s.indexOf('/');

        // if keyword /by is not present
        if (keyWordIndex == -1) {
            throw new InvalidDeadlineFormatException();
        }

        // correct format of deadline: yyyy-mm-ddTHH:mm
        String deadlineString = null;
        try {
            deadlineString = s.substring(keyWordIndex + SIZE_OF_BUFFER);
        } catch (Exception e) {
            // if keyword /by present but contains no value, default to 1 day deadline
            System.out.println("No deadline set, defaulting to 1 day later");
            deadlineString = LocalDateTime.now().plusDays(1).toString();

        }

        return dateTimeParser.getDateTimeObject(deadlineString);
    }

    /**
     * Get event start time as a string from user input and convert to LocalDateTime object
     *
     * @param s user input string that contains the event start time of task
     * @return LocalDateTime start time of event task
     *
     * @throws InvalidEventFormatException format of event is wrong, example: event <name> /from DateTime /to DateTime
     * @throws InvalidDateTimeException the date and time of the input string is in
     *
     */
    public LocalDateTime getEventStart(String s) throws InvalidDateTimeException, InvalidEventFormatException {
        int SIZE_OF_BUFFER = 6;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');
        if (startTimeIndex == -1) {
            throw new InvalidEventFormatException();
        }

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex+1);
        String eventStartString = s.substring(startTimeIndex + SIZE_OF_BUFFER, endTimeIndex-1);

        return dateTimeParser.getDateTimeObject(eventStartString);
    }

    /**
     * Get event end time as a string from user input and convert to LocalDateTime object
     *
     * @param s user input string that contains the event end time of task
     * @return LocalDateTime end time of event task
     *
     * @throws InvalidEventFormatException format of event is wrong, example: event <name> /from DateTime /to DateTime
     * @throws InvalidDateTimeException the date and time of the input string is in
     *
     */
    public LocalDateTime getEventEnd(String s) throws InvalidDateTimeException, InvalidEventFormatException {
        int SIZE_OF_BUFFER = 4;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex+1);
        if (endTimeIndex == -1) {
            throw new InvalidEventFormatException();
        }
        String eventEndString = s.substring(endTimeIndex + SIZE_OF_BUFFER);
        return dateTimeParser.getDateTimeObject(eventEndString);
    }
}
