package duke.exception;

/**
 * Indicates an error caused by invalid input when creating a task.
 */
public class DukeTaskException extends Exception{

    private static final String TODO_FORMAT = "todo 'task'";
    private static final String DEADLINE_FORMAT = "deadline 'task' /by 'specified deadline'";
    private static final String EVENT_FORMAT = "event 'task' /from 'specified start date' /to 'specified end date'";


    /**
     * Sends out an error message that the input does not match with the specified format.
     *
     * @param command The type of task.
     * @param input The input with the invalid format.
     */
    public void handleDukeTaskException(String command, String input) {
        System.out.println("\tSorry your " + command + "'s format is invalid.");
        System.out.println("\tYour input should be in this format:");

        if (command.equals("todo")) {
            System.out.println("\t\t" + TODO_FORMAT);
        } else if (command.equals("deadline")) {
            System.out.println("\t\t" + DEADLINE_FORMAT);
        } else {
            System.out.println("\t\t" + EVENT_FORMAT);
        }

        System.out.println("\tYour input is: ");
        System.out.println("\t\t" + command + " " + input);
    }
}
