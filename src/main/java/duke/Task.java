package duke;

/**
 * Task Superclass.
 */
public class Task {

    public String description;
    public boolean isDone;
    public String taskType;
    public String deadline;
    public String from;
    public String to;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return Checkmark based on whether the task is completed or not as a string.
     */
    public String getStatusIcon(){
        if (this.isDone){
            return "[X]";
        }
        return "[ ]";
    }

    /**
     * @return Task type icon as a string.
     */
    public String getTaskTypeIcon(){
        switch (this.taskType){
        case "todo":
            return "[T]";
        case "deadline":
            return "[D]";
        case "event":
            return "[E]";
        default:
            return "[ ]";
        }
    }

    /**
     * Print the task in a presentable format with the task type icon,
     * status icon and description.
     */
    public void printTask(){
        switch (taskType) {
        case "todo":
            System.out.print(getTaskTypeIcon() + getStatusIcon() + " " + description + "\n");
            break;
        case "deadline":
            System.out.print(getTaskTypeIcon() + getStatusIcon() + " " + description
                    + "(by:"+deadline+")" + "\n");
            break;
        case "event":
            System.out.print(getTaskTypeIcon() + getStatusIcon() + " " + description
                    + " (from: " + from + " to: " + to+")" + "\n");
            break;
        }
    }

    /**
     * @return String of all the task's features as a string so it can be written
     *         to a text file
     */
    public String taskFormatted(){
        switch (taskType) {
        case "todo":
            return ("T" + "|" + isDone + "|" + description + "\n");
        case "deadline":
            return ("D" + "|" + isDone + "|" + description
                    + "|" + deadline + "\n");
        case "event":
             return ("E" + "|" + isDone + "|" + description
                    + "|" + from + "|" + to + "\n");
        }
        return "test";
    }
}
