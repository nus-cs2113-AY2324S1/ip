package duke;
import java.util.Arrays;
import java.util.Collections;

public class DukeException {

    protected String command;
    protected String[] userInput;
    public boolean exception;

    public DukeException(String[] userInput){
        this.userInput = userInput;
        this.command = userInput[0];
        this.exception = false;
    }

    public void checkInput(int size){
        // Repeated commands, empty entries
        switch (this.command) {
        case "mark":
        case "unmark":
            if (this.userInput.length == 2 && Integer.parseInt(this.userInput[1]) > 0){
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
            }
            exception = true;
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

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void customError(String message){
        printLine();
        System.out.println("    ☹ OOPS!!! " + message + " :-(");
        printLine();
        exception = true;
    }

    public void unknownCommand(){
        printLine();
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
        exception = true;
    }

    private void emptyDescription(){
        printLine();
        System.out.println("    ☹ OOPS!!! The description of a " + this.command + " cannot be empty.");
        printLine();
        exception = true;
    }

    public void missingKeyword(){
        printLine();
        System.out.println("    ☹ OOPS!!! You are missing an important keyword");
        printLine();
        exception = true;
    }
}
