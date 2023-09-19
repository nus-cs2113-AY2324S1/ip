package duke;

public class Deadline extends Task{

    private String dueDate;

    public Deadline(String description, String dueDate){
        super(description);
        this.dueDate = dueDate;

    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    @Override
    public String toFileString() {
        return ("D | " + super.toFileString() + " | " + dueDate);
    }
}
