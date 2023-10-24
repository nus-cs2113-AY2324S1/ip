public class KenException extends Exception {
    public KenException(String message) {
        super(message);
    }
}


class InvalidCommandException extends KenException {
    public InvalidCommandException() {
        super("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' 'delete [number],' or 'mark [number].");
    }
}


class TaskNotFoundException extends KenException {
    public TaskNotFoundException() {
        super("I can't find this task. Please provide a valid task number.");
    }
}

class WrongFormatException extends KenException {
    public WrongFormatException() {
        super("Wrong format!! Please provide the number of the task.");
    }
}




