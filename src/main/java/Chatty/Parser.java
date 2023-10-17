/**
 * The Parser class handles all the inputs and decides what to do next
 */
package Chatty;
import Chatty.Command.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    static Ui ui = new Ui();

    /**
     * Starts processing the command
     * @param input input of the user
     * @param tasks the list of current tasks
     * @return a command which will be executed later
     */
    public static Command parseCommand(String input, TaskList tasks) {
        String[] words = input.split(" ");
        String command = words[0].toLowerCase();
        switch (command) {
        case "list":
            return new listCommand(input);
        case "mark":
            //System.out.println("mark");
            return new markCommand(input, true);
        case "unmark":
            return new markCommand(input, false);
        case "todo":
            if (input.length() > 5) {
                return new addCommand(input, 'T');
            } else {
                ui.printMessage(Ui.LINE + "\n☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            int byIndex = input.indexOf("/by");
            if (byIndex != -1 && input.length() > 9 && byIndex != 9) {
                return new addCommand(input, 'D');
            } else if (byIndex == -1) {
                ui.printMessage("☹ OOPS!!! Invalid Deadline format. Please use /by to specify a deadline.");
                ui.printMessage("E.g. deadline homework /by 2023-10-06");
            } else if (byIndex == 9 || input.length() <= 9) {
                ui.printMessage("☹ OOPS!!! Description of Deadline task needed!");
                ui.printMessage("E.g. deadline homework /by 2023-10-06");
            } else {
                ui.printMessage("☹ OOPS!!! Unknown Error adding a deadline. Try Again.");
                ui.printMessage("E.g. deadline homework /by 2023-10-06");
            }
            break;
        case "event":
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex != -1 && toIndex != -1 && input.length() > 6 && fromIndex != 6 && toIndex != fromIndex + 6) {
                return new addCommand(input, 'E');
            } else if (fromIndex == -1 || toIndex == -1 || toIndex == fromIndex + 6) {
                ui.printMessage("☹ OOPS!!! Invalid Event format. Please use /from to specify the start of an event and /to to specify the end.");
                ui.printMessage("E.g. event project meeting /from 2023-10-06 4pm /to 2023-10-07");
            } else if (input.length() <= 6 || fromIndex == 6) {
                ui.printMessage("☹ OOPS!!! Description of Event task needed!");
                ui.printMessage("E.g. event project meeting /from 2023-10-06 4pm /to 2023-10-07");
            } else {
                ui.printMessage("☹ OOPS!!! Unknown Error adding an event. Try Again.");
                ui.printMessage("E.g. event project meeting /from 2023-10-06 4pm /to 2023-10-07");
            }
            break;
        case "delete":
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= 0 && index < tasks.size()) {
                return new removeCommand(input);
            } else {
                ui.printMessage("☹ OOPS!!! Invalid task number to delete.");
            }
            break;
        case "find":
            return new findCommand(input);
        case "showdate":
            try {
                String dateToPrint = input.substring(10).trim(); // Extract the date from user input
                LocalDate specifiedDate = LocalDate.parse(dateToPrint, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new printDate(input);
            } catch (Exception e) {
                ui.printMessage("☹ OOPS!!! Invalid date.");
            }
            break;
        default:
            return new badCommand(input);
        }
        return new Command(input);
    }
}
