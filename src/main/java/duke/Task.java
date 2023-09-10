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
}
