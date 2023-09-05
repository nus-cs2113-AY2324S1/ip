public class Task {
    public  String toBeDone;
    private boolean isCompleted;
    public String[] markAsDone;
    public String[] taskType;
    public String dueDate;
    public String startTime;
    public String endTime;

    public Task(String newTask) {
        toBeDone = newTask;
        isCompleted = false;
        markAsDone = new String[1];
        markAsDone[0] = " ";
        taskType  = new String[1];
        taskType[0] = " ";

    }
    public void setDone(){
        isCompleted = true;
        markAsDone[0] ="X";
    }

    public void setNotDone(){
        isCompleted = false;
        markAsDone[0] =" ";
    }
}
