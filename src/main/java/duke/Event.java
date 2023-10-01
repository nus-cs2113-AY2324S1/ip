package duke;
import java.util.Arrays;

public class Event extends Task{

    public Event(String[] userInput, boolean read) {
        super(getDescription(userInput, read));
        super.taskType = "event";
        super.from = getFrom(userInput, read);
        super.to = getTo(userInput, read);;
    }

    private static String getDescription(String[] userInput, boolean read){
        int fromIndex = Arrays.asList(userInput).indexOf("/from");
        if (read){
            return userInput[2];
        }
        return String.join(" ", Arrays.copyOfRange(userInput, 1, fromIndex)) + " ";
    }

    private static String getFrom(String[] userInput, boolean read){
        if (read){
            return userInput[3];
        }
        int fromIndex = Arrays.asList(userInput).indexOf("/from");
        int toIndex = Arrays.asList(userInput).indexOf("/to");
        return String.join(" ", Arrays.copyOfRange(userInput, fromIndex + 1, toIndex));
    }

    private static String getTo(String[] userInput, boolean read){
        if (read){
            return userInput[4];
        }
        int toIndex = Arrays.asList(userInput).indexOf("/to");
        return String.join(" ", Arrays.copyOfRange(userInput, toIndex + 1, userInput.length));
    }

}
