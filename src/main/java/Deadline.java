public class Deadline extends Task{
    private String by;
    public Deadline(String tasks, String By) {
        super(tasks);
        this.by = By;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

}
