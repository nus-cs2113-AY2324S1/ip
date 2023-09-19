public class Deadline extends Todo {
    protected String byDate;

    public Deadline(String echo) {
        super(echo);
        //setByDate(date);
        String taskDescription = echo.substring(8);
        int slashCut = taskDescription.indexOf("/");
        byDate = taskDescription.substring(slashCut + 1);
        description = taskDescription.substring(0, slashCut);
        if (description.length() <= 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public String getByDate() {
        return byDate;
    }

    public void setByDate(String date) {
        int spaceCut = date.indexOf(" ");
        byDate = date.substring(spaceCut + 1);
    }

    @Override
    public String toString() {
        //super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "\t[D]" + isDoneString + getDescription() + "(do by: " + getByDate() + ")";
    }
}
