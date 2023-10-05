package torchie.parser;

import torchie.exception.InvalidIndexException;
import torchie.exception.InvalidEventFormatException;
import torchie.exception.TorchieException;
import torchie.exception.InvalidDeadlineFormatException;
import torchie.exception.MissingTaskNameException;
import torchie.parser.DateTimeParser;
import java.time.LocalDateTime;

public class TaskDetailsParser {
    public TaskDetailsParser() {
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

        String deadlineString = s.substring(keyWordIndex + SIZE_OF_BUFFER);
        DateTimeParser dateTimeParser = new DateTimeParser(deadlineString);

        return dateTimeParser.getDateTimeObject(deadlineString);
    }

    public String getEventStart(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 6;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');
        if (startTimeIndex == -1) {
            throw new InvalidEventFormatException();
        }

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex+1);
        return s.substring(startTimeIndex + SIZE_OF_BUFFER, endTimeIndex-1);
    }

    public String getEventEnd(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 4;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex+1);
        if (endTimeIndex == -1) {
            throw new InvalidEventFormatException();
        }
        return s.substring(endTimeIndex + SIZE_OF_BUFFER);
    }
}
