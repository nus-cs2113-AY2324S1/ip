public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getIsDone(){
        return (isDone ? "X" : " ");
    }
}
