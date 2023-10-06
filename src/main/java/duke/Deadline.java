package duke;

import java.util.Arrays;

/**
 * Class that inherits the Task class which stores objects that are of Deadline type.
 */
public class Deadline extends Task {

    public Deadline(String[] userInput, boolean read) {
        super(getDescription(userInput, read));
        super.taskType = "deadline";
        super.deadline = getDeadline(userInput, read);
    }

    /**
     * Method to get the description from the user input.
     * @param userInput String containing the entire user input.
     * @param read Boolean flag to differentiate whether it is being read from the txt file or
     *             from the taskList.
     * @return the description of the task entered by the user.
     */
    private static String getDescription(String[] userInput, boolean read){
        int byIndex = Arrays.asList(userInput).indexOf("/by");
        int startIndex = 1;
        if (read){
            return userInput[2];
        }

        return String.join(" ", Arrays.copyOfRange(userInput, startIndex, byIndex)) + " ";
    }

    /**
     * method to get the deadline from the user input.
     * @param userInput String containing the entire user input.
     * @param read Boolean flag to differentiate whether it is being read from the txt file or
     *             from the taskList.
     * @return deadline of the task as entered by the user.
     */
    private static String getDeadline(String[] userInput, boolean read){
        if (read) {
            return userInput[3];
        }
        int byIndex = Arrays.asList(userInput).indexOf("/by");
        return String.join(" ", Arrays.copyOfRange(userInput, byIndex + 1, userInput.length));
    }
}
