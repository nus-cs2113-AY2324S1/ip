package Task;

/**
 * Class containing info about a task
 * Info includes name and whether the task is completed
 */
public class Task {
    protected String name;
    protected boolean done;

    public Task(String name){
        this.name = name;
        this.done = false;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param done Whether the task is done.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Expresses the task in a String format.
     *
     * @return String containing info of the Task
     */
    @Override
    public String toString(){
        String out = "["
                + (done ? "X" : " ")
                + "] " + this.name;
        return out;
    }

    /**
     * Formats class into a row in the save file
     * Each parameter separated by " /"
     * Format is [NAME] /[DONE] /[TYPE] /{any additional parameters}
     *
     * @return String formatted for saving in a .txt
     */
    public String toFileLine() {
        return name + " /" + (done ? "true" : "false");
    }
}
