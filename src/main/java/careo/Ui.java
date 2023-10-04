package careo;

public class Ui {
    /**
     * Prints a line of dashes indented by four spaces.
     */
    private void printHorizontalLine() {
        System.out.println("    " + "-".repeat(76));
    }

    /**
     * Prints a polite introduction and offer for help.
     */
    public void printIntroduction() {
        printHorizontalLine();
        System.out.println("    Hello! I'm Careo");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints a polite goodbye message.
     */
    public void printFarewell() {
        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints a list of all tasks and their status.
     */
    public void listTasks(TaskList tasks) {
        int counter = 1;

        printHorizontalLine();
        for (Task task : tasks.getTasks()) {
            System.out.println("    " + counter + "." + task);
            counter++;
        }
        printHorizontalLine();
    }

    /**
     * Prints that loading from a persistence file was unsuccessful.
     */
    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("    No file specifying saved tasks was present.");
        System.out.println("    Creating a new one; you can now start inputting your tasks.");
        printHorizontalLine();
    }

    /**
     * Prints that the input was invalid.
     */
    public void showInvalidInputError() {
        printHorizontalLine();
        System.out.println("    Invalid input!");
        System.out.println("    Please make sure that your input is in the proper format and try again");
        printHorizontalLine();
    }

    /**
     * Prints that a command was missing arguments.
     *
     * @param input The name of the command whose arguments were missing.
     */
    public void showCommandArgumentMissingError(String input) {
        printHorizontalLine();
        System.out.println("    ☹ OOPS!!! The description of a " + input + " cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Printst that the command was not recognized.
     */
    public void showUnrecognizedCommandError() {
        printHorizontalLine();
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printHorizontalLine();
    }

    /**
     * Prints that the task list is currently empty.
     */
    public void printTaskListEmpty() {
        printHorizontalLine();
        System.out.println("    Sorry, there are no tasks yet!");
        System.out.println("    Add a task and then try again.");
        printHorizontalLine();
    }

    /**
     * Prints that the chosen task index is invalid (too high or too low).
     *
     * @param tasks The TaskList which is supposed to be indexed into.
     */
    public void showInvalidTaskIndexError(TaskList tasks) {
        printHorizontalLine();
        System.out.println("    Sorry, the chosen task index is invalid!");
        System.out.println("    Task index must be between 1 (inclusive) and " + tasks.size() +
                " (inclusive).");
        printHorizontalLine();
    }

    /**
     * Prints that a mark or unmark operation completed sucessfully.
     *
     * @param markOrUnmark Whether the operation is a mark or an unmark operation.
     * @param selectedTask Which task is supposed to be (un)selected.
     */
    public void printSuccessfulMarkOrUnmark(MarkOrUnmark markOrUnmark, Task selectedTask) {
        printHorizontalLine();
        if (markOrUnmark == MarkOrUnmark.MARK) {
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        System.out.println("      " + selectedTask);
        printHorizontalLine();
    }

    /**
     * Prints that a new task was sucessfully added to the task list.
     *
     * @param newTask New Task that is being added.
     * @param tasks TaskList onto which the new task was added.
     */
    public void printSuccessfullyAddedTask(Task newTask, TaskList tasks) {
        printHorizontalLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + newTask.toString());

        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list.");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        }

        printHorizontalLine();
    }

    /**
     * Prints that a task was successfully removed from the task list.
     *
     * @param removedTask The task that was just removed.
     * @param tasks TaskList from which the task was just removed.
     */
    public void printSuccessfullyRemovedTask(Task removedTask, TaskList tasks) {
        printHorizontalLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + removedTask.toString());
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list.");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        }
        printHorizontalLine();
    }
}
