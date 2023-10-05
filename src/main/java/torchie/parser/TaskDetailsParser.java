package torchie.parser;

import torchie.exception.InvalidIndexException;
import torchie.exception.InvalidEventFormatException;
import torchie.exception.TorchieException;
import torchie.exception.InvalidDeadlineFormatException;
import torchie.exception.MissingTaskNameException;
import torchie.parser.DateTimeParser;
import java.time.LocalDateTime;

public class TaskDetailsParser {

    private DateTimeParser dateTimeParser;
    public TaskDetailsParser() {
        dateTimeParser = new DateTimeParser();
    }

    public String getIndex(String s) throws InvalidIndexException {
        // split sentence into 2 parts, first word and everything else
        String[] words = s.split(" ", 2);

        if (words.length<2) {
            throw new InvalidIndexException();
        }

        // making sure content stops before the key characters such as /
        String content = words[1];

        if (content.indexOf('/') != -1) {
            int keyWordIndex = content.indexOf('/');
            content = content.substring(0, keyWordIndex - 1);
        }

        return content;
    }

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

    public LocalDateTime getDeadlineDate(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 4;
        int keyWordIndex = s.indexOf('/');

        // if keyword /by is not present
        if (keyWordIndex == -1) {
            throw new InvalidDeadlineFormatException();
        }

        // correct format of deadline: yyyy-mm-ddTHH:mm
        String deadlineString = s.substring(keyWordIndex + SIZE_OF_BUFFER);

        return dateTimeParser.getDateTimeObject(deadlineString);
    }

    public LocalDateTime getEventStart(String s) throws TorchieException {
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

    public LocalDateTime getEventEnd(String s) throws TorchieException {
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
