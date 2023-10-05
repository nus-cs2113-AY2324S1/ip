package luke.tasks;
import luke.user.LukeTimeError;

public class Deadline extends Task {
    protected String date;
    protected String deadlineGuide = "\tdeadline <description> /by <date>";

    public Deadline(String taskDescription) throws LukeTimeError {
        super(taskDescription);

        int slashCut = taskDescription.indexOf("/");
        if (slashCut <= 0) {
            System.out.println("\tThere is a missing task description. Please follow this format:");
            printGuide();
            throw new LukeTimeError();
        }

        description = taskDescription.substring(0, slashCut);
/*
        if (description.length() <= 1) {
            System.out.println("length prob");
            throw new IndexOutOfBoundsException();
        }
 */

        setDate(taskDescription.substring(slashCut + 1));
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateString) throws LukeTimeError {
        String[] words = dateString.split(" ");
        if (!words[0].equals("by")) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeTimeError();
        }
        int spaceCut = dateString.indexOf(" ");
        if (spaceCut <= 0) {
            System.out.println("\tThere is a missing date. Please follow this format:");
            printGuide();
            throw new LukeTimeError();
        }
        date = dateString.substring(spaceCut + 1);
    }

    /*@Override
    public String getType() {
        return "deadline";
    }

     */
    @Override
    public void printGuide() {
        System.out.println(deadlineGuide);
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

        return "\t[D]" + isDoneString + " " + getDescription() + "(do by: " + getDate() + ")";
    }

    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[D]" + isDoneString + getDescription() + "/by " + getDate();
    }
}
