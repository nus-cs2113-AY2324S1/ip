package Parser;

import Task.EmptyDescriptionException;
import Task.TaskList;
import Ui.Ui;

/**
 * Provides methods to parse inputs into the CLI.
 */
public class Parser {

    /**
     * Removes the command word in front of an argument entered into the command line.
     * @param in Input string containing the command word.
     * @return String with the command word removed.
     * @throws EmptyDescriptionException When the input string only comprises the command word.
     */
    public static String removeCommandWord(String in) throws EmptyDescriptionException {
        int firstSpaceIndex = in.indexOf(" ") + 1;

        String out = in.substring(firstSpaceIndex);

        if(firstSpaceIndex == 0 || firstSpaceIndex >= in.length()){
            // Throws exception when name of task is left empty
            throw new EmptyDescriptionException();
        }

        return out;
    }

    /**
     * Parses the input string to determine which command to carry out.
     *
     * @param buf String to be parsed.
     * @param itemList List of tasks already entered.
     */
    public static void parse(String buf, TaskList itemList){
        switch(buf.toLowerCase().split(" ")[0]) {
            case "list":
                itemList.listItems();
                break;

            case "mark":
                itemList.markTask(Integer.parseInt(buf.split(" ")[1]));
                break;

            case "unmark":
                itemList.unmarkTask(Integer.parseInt(buf.split(" ")[1]));
                break;

            case "todo":
                itemList.addTodo(buf);
                break;

            case "deadline":
                itemList.addDeadline(buf);
                break;

            case "event":
                itemList.addEvent(buf);
                break;

            case "delete":
                itemList.delete(Integer.parseInt(buf.split(" ")[1]));
                break;

            case "find":
                itemList.findItem(buf.split(" ")[1]);
                break;

            default:
                Ui.reportMissingCommandWord();
                break;
        }
    }
}
