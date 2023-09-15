package exception;

public class DukeException extends Exception {

    public DukeException() {
    }

    public DukeException(String message) {
        super(message);
    }

    private void sendIncorrectMsg(String taskType){
        System.out.println("Sorry, your command for " + taskType + " task is not in the correct format. Please try again.");
    }

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

        case "delete":
            break;

        default:
            //for case without correct start and empty command
            System.out.println("Please start with the supported task types: 'todo', 'deadline', 'event'. ");
        }



    }
}

