package torchie.task;

public class ToDo extends Task {
    public ToDo(String description){
        super(description);
    }

    /**
     * Used to display task object in a user-friendly way
     *
     * @return String that shows a user-friendly version of task object
     *
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Print a line to tell users that task was added successfully
     *
     */
    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask(this.toString());
    }


    /**
     * Change task to a format that will be used to store in file
     *
     * @return String raw format to be saved in file
     *
     */
    @Override
    public String toFileFormat() {
        return ("T | " + super.toFileFormat());
    }

    /**
     * Print a line to tell users that task was deleted successfully
     *
     */
    @Override
    public void announceTaskDelete() {
        super.announceTaskDelete();
        super.printTask(this.toString());
    }
}
