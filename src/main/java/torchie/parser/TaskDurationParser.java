package torchie.parser;

import torchie.exception.TorchieException;

public class TaskDurationParser {
    public TaskDurationParser() {
    }

    public String getDeadlineDate(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 4;
        int keyWordIndex = s.indexOf('/');

        if (keyWordIndex == -1) {
            throw new TorchieException();
        }
        return s.substring(keyWordIndex + SIZE_OF_BUFFER);
    }

    public String getEventStart(String s) throws TorchieException {
        int SIZE_OF_BUFFER = 6;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');
        if (startTimeIndex == -1) {
            throw new TorchieException();
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
            throw new TorchieException();
        }
        return s.substring(endTimeIndex + SIZE_OF_BUFFER);
    }
}
