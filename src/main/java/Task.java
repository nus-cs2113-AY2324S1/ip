public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String deadline;
    protected String from;
    protected String to;
    public Task(String description, String taskType){
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
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
