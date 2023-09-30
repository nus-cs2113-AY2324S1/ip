package chattie.error;

public class ChattieException extends Exception{
    public ChattieException(ErrorType e) {
        switch(e) {
        case TODO:
            System.out.println("\tOh no! Todo is empty :(");
            break;
        case EMPTY_DEADLINE:
            System.out.println("\tOh no! Deadline is empty :(");
            break;
        case INVALID_DEADLINE:
            System.out.println("\tOh no! Deadline has an invalid format :(");
            System.out.println("\tTo add a deadline: Type \"deadline [task] /by [deadline]\"");
            break;
        case EMPTY_EVENT:
            System.out.println("\tOh no! Event is empty :(");
            break;
        case INVALID_EVENT:
            System.out.println("\tOh no! Event has an invalid format :(");
            System.out.println("\tTo add an event: Type \"event [task] /from [start] /to [end]\"");
            break;
        case INVALID_COMMAND:
            System.out.println("\tOh no! I don't understand this command :(");
            break;
        case OUT_OF_BOUNDS:
            System.out.println("\tOh no! This task doesn't exist");
            break;
        }

    }
}
