package chat0pt.commands;

public class Task {
    private final String tasks;
    private boolean isMarked;

    public Task(String tasks) {
        this.tasks = tasks;
        this.isMarked = false;
    }

    public String getTasks() {
        return tasks;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    @Override
    public String toString(){
        String toReturn = "";
        if(isMarked){
            toReturn += "[X] ";
        } else{
            toReturn += "[ ] ";
        }
        return toReturn + tasks;
    }
}
