package duke;
import java.util.Arrays;

/**
 * Class that inherits the Task class which stores objects that are of Event type.
 */
public class Event extends Task{

    public Event(String[] userInput, boolean read) {
        super(getDescription(userInput, read));
        super.taskType = "event";
        super.from = getFrom(userInput, read);
        super.to = getTo(userInput, read);;
    }

    /**
     * Methof to get the description from the user input.
     * @param userInput String containing the entire user input.
     * @param read Boolean flag to differentiate whether it is being read from the txt file or
     *             from the taskList.
     * @return the description of the task entered by the user.
     */
    private static String getDescription(String[] userInput, boolean read){
        int fromIndex = Arrays.asList(userInput).indexOf("/from");
        if (read){
            return userInput[2];
        }
        return String.join(" ", Arrays.copyOfRange(userInput, 1, fromIndex)) + " ";
    }

    /**
     * Method to get the from timing from the user input.
     * @param userInput String containing the entire user input.
     * @param read Boolean flag to differentiate whether it is being read from the txt file or
     *             from the taskList.
     * @return 'from' date/time entered by the user
     */
    private static String getFrom(String[] userInput, boolean read){
        if (read){
            return userInput[3];
        }
        int fromIndex = Arrays.asList(userInput).indexOf("/from");
        int toIndex = Arrays.asList(userInput).indexOf("/to");
        return String.join(" ", Arrays.copyOfRange(userInput, fromIndex + 1, toIndex));
    }

    /**
     * Method to get the to timing from the user input.
     * @param userInput String containing the entire user input.
     * @param read Boolean flag to differentiate whether it is being read from the txt file or
     *             from the taskList.
     * @return 'to' date/time entered by the user
     */
    private static String getTo(String[] userInput, boolean read){
        if (read){
            return userInput[4];
        }
        int toIndex = Arrays.asList(userInput).indexOf("/to");
        return String.join(" ", Arrays.copyOfRange(userInput, toIndex + 1, userInput.length));
    }

}
