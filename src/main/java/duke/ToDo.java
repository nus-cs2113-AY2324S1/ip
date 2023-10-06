package duke;

import java.util.Arrays;

/**
 * Class that inherits the Task class which stores objects that are of ToDo type.
 */
public class ToDo extends Task{

    /**
     * Subclass Constructor
     * @param userInput String containing the entire user input.
     * @param read Boolean flag to differentiate whether it is being read from the txt file or
     *             from the taskList.
     */
    public ToDo(String[] userInput, boolean read) {
        super(getDescription(userInput, read));
        super.taskType = "todo";
    }

    /**
     * Method to get the formatted description from the text file.
     * @param userInput String containing the entire user input.
     * @param read Boolean flag to differentiate whether it is being read from the txt file or
     *             from the taskList.
     * @return the description of the task entered by the user.
     */
    private static String getDescription(String[] userInput, boolean read){
        int startIndex = 1;
        if (read){
            startIndex = 2;
        }
        return String.join(" ", Arrays.copyOfRange(userInput, startIndex, userInput.length));
    }
}
