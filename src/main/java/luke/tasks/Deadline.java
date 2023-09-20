
package luke.tasks;
import luke.errors.LukeTimeError;

public class Deadline extends Todo {
    protected String date;

    public Deadline(String echo) throws LukeTimeError {
        super(echo);

        String taskDescription = echo.substring(8);

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
}
