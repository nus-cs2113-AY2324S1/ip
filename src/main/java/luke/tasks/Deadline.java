package luke.tasks;
import luke.user.LukeTimeError;

public class Deadline extends Task {
    protected String date;

    public Deadline(String taskDescription) throws LukeTimeError {
        super(taskDescription);

        int slashCut = taskDescription.indexOf("/");
        if (slashCut <= 0) {
            throw new LukeTimeError();
        }

        setDate(taskDescription.substring(slashCut + 1));

        description = taskDescription.substring(0, slashCut);

        if (description.length() <= 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateString) throws LukeTimeError {
        String[] words = dateString.split(" ");
        if (!words[0].equals("by")) {
            throw new LukeTimeError();
        }
        int spaceCut = dateString.indexOf(" ");
        date = dateString.substring(spaceCut + 1);
    }

    @Override
    public String getType() {
        return "deadline";
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

        return "\t[D]" + isDoneString + getDescription() + "(do by: " + getDate() + ")";
    }

    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[D]" + isDoneString + " deadline" + getDescription() + "/by " + getDate();
    }
}
