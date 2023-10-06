package luke.tasks;
import luke.user.LukeException;

/**
 * The Deadline Class represents a task of type "Deadline" in the LukeTime application.
 * It extends the Task class and includes specific behavior for deadline tasks.
 */
public class Deadline extends Task {
    protected String date;
    protected String deadlineGuide = "\tdeadline <description> /by <date>";

    /**
     * Constructs a Deadline task with the specified task description and deadline date.
     *
     * @param taskDescription The description of the deadline task including the deadline date.
     * @throws LukeException If there are syntax or formatting errors in the task description.
     */
    public Deadline(String taskDescription) throws LukeException {
        super(taskDescription);

        int slashCut = taskDescription.indexOf("/");
        if (slashCut <= 0) {
            System.out.println("\tThere is a missing task description. Please follow this format:");
            printGuide();
            throw new LukeException();
        }

        description = taskDescription.substring(0, slashCut);

        setDate(taskDescription.substring(slashCut + 1));
    }

    /**
     * Gets the deadline date of the deadline task.
     *
     * @return The deadline date of the deadline task.
     */
    public String getDate() {
        return date;
    }

    /**
     * Parses and sets the deadline date of the deadline task from the provided date string.
     *
     * @param dateString The date string containing the deadline date information.
     * @throws LukeException If there are syntax or formatting errors in the date string.
     */
    public void setDate(String dateString) throws LukeException {
        String[] words = dateString.split(" ");
        if (!words[0].equals("by")) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeException();
        }
        int spaceCut = dateString.indexOf(" ");
        if (spaceCut <= 0) {
            System.out.println("\tThere is a missing date. Please follow this format:");
            printGuide();
            throw new LukeException();
        }
        date = dateString.substring(spaceCut + 1);
    }

    /**
     * Displays a guide on how to format a deadline task when printing an error message.
     */
    @Override
    public void printGuide() {
        System.out.println(deadlineGuide);
    }

    /**
     * Returns a string representation of the deadline task, including its completion status and deadline date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "\t[D]" + isDoneString + " " + getDescription() + "(do by: " + getDate() + ")";
    }

    /**
     * Returns a string representation of the deadline task for memory storage.
     *
     * @return A string representation of the deadline task for memory storage.
     */
    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[D]" + isDoneString + " " + getDescription() + "/by " + getDate();
    }
}
