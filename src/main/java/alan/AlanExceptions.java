package alan;

public class AlanExceptions extends Exception {
    public static final String INVALID_INPUT_COMMAND_MESSAGE = "Oof, I have no idea what are you saying duuude";
    public static final String EMPTY_DESCRIPTION_MESSAGE = "Oof Dude, you can't leave the description empty";
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Oof the deadline command isn't quite right you gotta fix the format, bro...\n[Remember it's: <description> /by <date>]";
    public static final String INVALID_EVENT_FROM_FORMAT_MESSAGE = "Oof duude, your /from formatting is whack\n[Remember it's: <description> /from <date> /to <date>]";
    public static final String INVALID_EVENT_TO_FORMAT_MESSAGE = "Oof my man, you need to work on that /to formatting\n[Remember it's: <description> /from <date> /to <date>]";
    public static final String INVALID_TASK_NUMBER_MESSAGE = "Oof maaaan there's no such task";
    public AlanExceptions(String errorMessage) {
        super(errorMessage);
    }

    public static void checkOutOfTasksIndex(int selectedIndex, int currentTasksIndex) throws AlanExceptions {
        int numberOfTasks = currentTasksIndex - 1;

        if (selectedIndex > numberOfTasks) {
            throw new AlanExceptions(INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    public static void invalidInputCommand() throws AlanExceptions {
        throw new AlanExceptions(INVALID_INPUT_COMMAND_MESSAGE);
    }

    public static void checkEmptyDescription(String userInput) throws AlanExceptions {
        String[] userInputWords = userInput.split(" ");

        if (userInputWords.length == 1) {
            throw new AlanExceptions(EMPTY_DESCRIPTION_MESSAGE);
        }
    }

    public static void checkDeadlineInputFormat(String[] words) throws AlanExceptions {
        if (words.length == 1) {
            throw new AlanExceptions(INVALID_DEADLINE_FORMAT_MESSAGE);
        }
    }

    public static void checkEventInputFromFormat(String[] splitDescriptionAndDate) throws AlanExceptions {
        if (splitDescriptionAndDate.length == 1) {
            throw new AlanExceptions(INVALID_EVENT_FROM_FORMAT_MESSAGE);
        }
    }

    public static void checkEventInputToFormat(String[] splitFromAndTo) throws AlanExceptions {
        if (splitFromAndTo.length == 1) {
            throw new AlanExceptions(INVALID_EVENT_TO_FORMAT_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
