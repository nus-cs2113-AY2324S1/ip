package exception;

public class OrientoException extends Exception {

    public OrientoException() {
    }

    public OrientoException(String message) {
        super(message);
    }

    private void sendIncorrectMsg(String taskType){
        System.out.println("Sorry, your command for " + taskType + " task is not in the correct format. Please try again.");
    }

    /**
     * Use to raises exception when incorrect format is found
     * tell the users what is correct format for the current input task
     * @param taskType represents the task type string such as "todo", "deadline", etc.
     */
    public void incorrectFormatException(String taskType) {
        switch (taskType){
        case "todo":
            sendIncorrectMsg("todo");
            System.out.println("Correct format: todo 'Your Todo Task' ");
            System.out.println("Example: todo read book");
            break;

        case "deadline":
            sendIncorrectMsg("deadline");
            System.out.println("Correct format: deadline 'Your Deadline Task' /by 'Due Time' ");
            System.out.println("Example: deadline return book /by Sunday 6pm");
            break;

        case "event":
            sendIncorrectMsg("event");
            System.out.println("Correct format: event 'Your Event Task' /from 'Starting Time' /to 'End Time' ");
            System.out.println("Example: event project meeting /from Mon 2pm /to 4pm");
            break;

        default:
            System.out.println("Please start with the supported task types: 'todo', 'deadline', 'event'. ");
        }



    }
}

