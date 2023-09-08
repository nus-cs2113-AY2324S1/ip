public class Deadlines extends Task{
    protected String deadline;
    public Deadlines(String description, String deadline){
        super(description + " (by: " + deadline + ")");
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    @Override
    public void printTask() {
        System.out.print("[D]");
        super.printTask();
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask();
    }

}
