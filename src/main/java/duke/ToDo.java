package duke;

import java.util.Arrays;

public class ToDo extends Task{

    public ToDo(String[] userInput, boolean read) {
        super(getDescription(userInput, read));
        super.taskType = "todo";
    }

    private static String getDescription(String[] userInput, boolean read){
        int startIndex = 1;
        if (read){
            startIndex = 2;
        }
        return String.join(" ", Arrays.copyOfRange(userInput, startIndex, userInput.length));
    }
}
