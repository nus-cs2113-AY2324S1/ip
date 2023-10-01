package duke;

import java.util.Arrays;

public class Deadline extends Task {


    public Deadline(String[] userInput, boolean read) {
        super(getDescription(userInput, read));
        super.taskType = "deadline";
        super.deadline = getDeadline(userInput, read);
    }

    private static String getDescription(String[] userInput, boolean read){
        int byIndex = Arrays.asList(userInput).indexOf("/by");
        int startIndex = 1;
        if (read){
            return userInput[2];
        }

        return String.join(" ", Arrays.copyOfRange(userInput, startIndex, byIndex)) + " ";
    }

    private static String getDeadline(String[] userInput, boolean read){
        if (read) {
            return userInput[3];
        }
        int byIndex = Arrays.asList(userInput).indexOf("/by");
        return String.join(" ", Arrays.copyOfRange(userInput, byIndex + 1, userInput.length));
    }
}
