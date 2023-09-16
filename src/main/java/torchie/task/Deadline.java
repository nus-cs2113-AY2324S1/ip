package torchie.task;

public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + deadline + ")");
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        super.printTask(this.toString());
    }

}
