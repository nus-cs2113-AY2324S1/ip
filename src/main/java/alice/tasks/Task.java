package alice.tasks;

public class Task {
    public static final String LINE = "____________________________________________________________\n";
    private static final String TAB_SPACE = "    ";

    public static final String SEPARATOR = " | ";
    private String description;
    private boolean isDone;
    

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone =  isDone;
    }

    /**
     * Printing status icon
     * @return a string in the format "[<status>]", "X" for done and " " for not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * To set isDone to true
     * and to print a message for user
     */
    public void markTask() {
        setIsDone(true);
        System.out.println(TAB_SPACE + "Nice! I've marked this task as done:");
        System.out.println(TAB_SPACE + TAB_SPACE + this.toString() + System.lineSeparator());
        System.out.println(LINE);
    }

    /**
     * To set isDone to false
     * and to print a message for user
     */
    public void unmarkTask() {
        setIsDone(false);
        System.out.println(TAB_SPACE + "OK, I've marked this task as not done yet:");
        System.out.println(TAB_SPACE + TAB_SPACE + this.toString() + System.lineSeparator());
        System.out.println(LINE);
    }

    public String toString() {
        String statusOfTask = "[" + getStatusIcon() + "] ";
        return statusOfTask + getDescription();
    }

    /**
     * Encodes the symbol and description of task into a string to be stored in the text file
     *
     * @return string in the format to be stored in text file
     */
    public String encode() {
        String isDoneSymbol = "0"; //default is unmarked
        if (isDone) {
            isDoneSymbol = "1";
        }
        return String.format("%s | %s", isDoneSymbol, getDescription());
    }
}