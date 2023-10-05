package duke;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class to check the user input and check for format violations or
 * unrecognised commands and/or operations.
 */
public class DukeException {

    protected String command;
    protected String[] userInput;
    public boolean exception = false;
    private final Ui ui = new Ui();

    public DukeException(String[] userInput){
        this.userInput = userInput;
        this.command = userInput[0];
    }

    /**
     * @param size size of the taskList to check for out of bounds value.
     */
    public void checkInput(int size){
        // Repeated commands, empty entries
        switch (this.command) {
        case "mark":
        case "unmark":
            if (this.userInput.length == 2 && Integer.parseInt(this.userInput[1]) > 0 && Integer.parseInt(this.userInput[1]) <= size){
                break;
            } else {
                customError("You have entered an invalid index");
                this.exception = true;
            }
            break;
        case "bye":
        case "list":
            if (this.userInput.length < 2){
                break;
            } else {
                this.exception = true;
                unknownCommand();
            }
            break;
        case "todo":
        case "find":
            if (this.userInput.length <= 1) {
                emptyDescription();
                this.exception = true;
            }
            break;
        case "deadline":
            int byCount = Collections.frequency(Arrays.asList(this.userInput), "/by");
            if (byCount == 0 || byCount > 1){
                missingKeyword();
                this.exception = true;
            }
            break;
        case "event":
            int fromIndex = Arrays.asList(userInput).indexOf("/from");
            int toIndex = Arrays.asList(userInput).indexOf("/to");
            if (fromIndex == -1 || toIndex == -1){
                missingKeyword();
                exception = true;
            }
            return;
        case "delete":
            if (this.userInput.length == 2 && (Integer.parseInt(this.userInput[1]) > 0 && Integer.parseInt(this.userInput[1]) <= size)){
                break;
            } else {
                customError("You have entered an invalid index");
                this.exception = true;
            }
            break;
        default:
            unknownCommand();
        }
    }


    /**
     * @param message Message to be output.
     */
    public void customError(String message){
        ui.printLine();
        ui.echo("☹ OOPS!!! " + message + " :-(\n");
        ui.printLine();
        exception = true;
    }

    /**
     * Method to print general message when the command is not recognised.
     */
    public void unknownCommand(){
        ui.printLine();
        ui.echo("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        ui.printLine();
        exception = true;
    }

    /**
     * Method to print general message when the description of a task is empty.
     */
    private void emptyDescription(){
        ui.printLine();
        ui.echo("☹ OOPS!!! The description of a " + this.command + " cannot be empty.\n");
        ui.printLine();
        exception = true;
    }

    /**
     * Method to print general message when the keyword is missing.
     */
    public void missingKeyword(){
        ui.printLine();
        ui.echo("☹ OOPS!!! You are missing an important keyword\n");
        ui.printLine();
        exception = true;
    }
}