package magpie.task;

public class Task {

    protected String description;
    protected boolean isDone;

    protected String textToWrite;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {

        String done = " ";

        if (isDone) {
            done = "X";
        }
        return done;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public static int parseBooleanToInt(boolean input) {

        if (input){
            return 1;
        }
        else{
            return 0;
        }
    }

    public String getTextToWrite() {
        return this.textToWrite;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
