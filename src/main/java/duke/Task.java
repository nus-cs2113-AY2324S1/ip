package duke;

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

    public String getStatusIcon(){
        if (this.isDone){
            return "[X]";
        }
        return "[ ]";
    }

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
