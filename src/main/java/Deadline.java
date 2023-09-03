public class Deadline extends Task{
    protected String date;
    public Deadline (String description, String date) {
        super(description);
        this.date = date;
        Task.numberOfTasks ++;
    }
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "[D][" + super.getStatusIcon() + "]" + description
                + " (by:" + getDate()+ ")" + System.lineSeparator() + "Now you have " + super.getNumberOfTasks() + " tasks in the list.";
    }
}
