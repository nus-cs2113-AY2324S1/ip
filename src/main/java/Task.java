public class Task {
    public  String toBeDone;
    private boolean completed;
    public String[] markAsDone;

    public Task(String newTask) {
        toBeDone = newTask;
        completed = false;
        markAsDone = new String[1];
        markAsDone[0] = " ";
    }
    public void setDone(){
        completed = true;
        markAsDone[0] ="X";
    }

    public void setNotDone(){
        completed = false;
        markAsDone[0] =" ";
    }
}
