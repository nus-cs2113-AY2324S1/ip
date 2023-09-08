import java.util.ArrayList;
import java.util.List;
public class ResponseProcessor {
    taskProcessor taskHandler;

    public ResponseProcessor() {
        this.taskHandler = new taskProcessor();
    }
    public void process(String response) {
        if ("list".equalsIgnoreCase(response)) {
            taskHandler.listProcessor(response);
        } else if (response.toLowerCase().startsWith("mark".toLowerCase())) {
            String value = response.split(" ")[1];
            if (isInt(value)) {
                taskHandler.markProcessor(Integer.parseInt(value) - 1);
            }
        } else if (response.toLowerCase().startsWith("unmark".toLowerCase())) {
            String value = response.split(" ")[1];
            if (isInt(value)) {
                taskHandler.unmarkProcessor(Integer.parseInt(value) - 1);
            }
        } else if (response.toLowerCase().startsWith("todo".toLowerCase())){
            String temp = removeFirstWord(response);
            if (isValidTask(temp)){
                taskHandler.todoProcessor(temp);
            }
        } else if (response.toLowerCase().startsWith("event".toLowerCase())) {
            String temp = removeFirstWord(response);
            if (isValidTask(temp)){
                taskHandler.eventProcessor(temp);
            }
        } else if (response.toLowerCase().startsWith("deadline".toLowerCase())) {
            String temp = removeFirstWord(response);
            if (isValidTask(temp)){
                taskHandler.deadlineProcessor(temp);
            }
        } else{
            System.out.println("I dont understand masta! Type a command uwu!");
        }
    }

    public Boolean isInt(String value){
        try {
            int num = Integer.parseInt(value);
            if ((0 < num) && (num <= taskHandler.getSize())){
                return true;
            }
            System.out.println("That is not a valid number masta!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Please put in a number masta!");
            return false;
        }
    }

    public boolean isValidTask(String response){
        if ("".equalsIgnoreCase(response)){
            System.out.println("uwu master please put in the task name!");
            return false;
        }
        return true;
    }
    public static String removeFirstWord(String str) {
        int index = str.indexOf(" ");
        if (index == -1) {
            return "";
        }
        return str.substring(index + 1);
    }
}
