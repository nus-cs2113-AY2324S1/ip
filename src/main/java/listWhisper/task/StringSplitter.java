package listWhisper.task;

import listWhisper.exceptions.DescriptionFormatException;

import java.util.ArrayList;

public class StringSplitter {
    public static String[] splitInputIntoEventFormat(String description)
            throws DescriptionFormatException {
        String[] descriptionAndTime = description.split("/");

        if (descriptionAndTime.length != 3) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to add an event: " +
                            "event [event description] " +
                            "/from[start time and date] /to[end time and date]");
        }
        return descriptionAndTime;
    }

    //Split input into different formats
    public static String[] splitInputIntoDeadlineFormat(String description)
            throws DescriptionFormatException {
        String[] descriptionAndTime = description.split("/by");

        if (descriptionAndTime.length != 2) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to add a deadline: "
                            + "deadline [deadline description] /by[time and date of the deadline]");
        }
        return descriptionAndTime;
    }

    public static String[] splitData(String line) {
        return line.split(" ", 2);
    }

}

