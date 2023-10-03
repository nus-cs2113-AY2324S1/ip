package Task;

public class ToDo extends Task {

    public ToDo(String name){
        super(name);
    }
    public ToDo(String name, boolean done){
        super(name);
        this.done = done;
    }

    /**
     * Expresses the task in a String format.
     *
     * @return String containing info of the Task
     */
    @Override
    public String toString(){
        String out = "[T]["
                + (done ? "X" : " ")
                + "] " + super.name;
        return out;
    }

    /**
     * Formats class into a row in the save file
     * Each parameter separated by " /"
     * Format is [NAME] /[DONE] /[TYPE]
     *
     * @return String formatted for saving in a .txt
     */
    @Override
    public String toFileLine(){
        return super.toFileLine() + " /TODO";
    }
}
