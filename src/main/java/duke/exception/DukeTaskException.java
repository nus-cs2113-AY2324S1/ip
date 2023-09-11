package duke.exception;

public class DukeTaskException extends Exception{

    private static final String TODO_FORMAT = "todo 'task'";
    private static final String DEADLINE_FORMAT = "deadline 'task' /by 'specified deadline'";
    private static final String EVENT_FORMAT = "event 'task' /from 'specified start date' /to 'specified end date'";

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
