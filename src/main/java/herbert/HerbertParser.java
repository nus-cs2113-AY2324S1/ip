package herbert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class HerbertParser {

   public static int checkInputTaskIndex(String line) {
       if (line.split(" ").length != 2) {
           HerbertUI.printMessageInvalidInput(
                   "Please enter the task number you wish to change the status of."
           );
           return -1;
       }

       return 0;
   }

    public static int extractTaskIndex(String line) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            HerbertUI.printMessageInvalidInput("Task index must be a positive integer.");
            return -1;
        }

        return taskIndex;
    }

    public static int verifyTaskIndex(int taskIndex, TaskList taskList) {
        if (taskIndex >= taskList.size()) {
            HerbertUI.printMessageInvalidInput("No such task exists!");
            return -1;
        }
        if (taskIndex < 0) {
            HerbertUI.printMessageInvalidInput("Task index must be a positive integer.");
            return -1;
        }
        return 0;
    }

    public static int checkInputAddTask(String line) {
        String[] words = line.split(" ");
        if (words.length < 2) {
            HerbertUI.printMessageInvalidInput();
            return -1;
        }
        return 0;
    }

    public static String[] getDeadlineDetails(String line) {
        String patternString = "^deadline\\s+(.+?)\\s+/by\\s+(.+)$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            HerbertUI.printMessageInvalidInput();
            return null;
        }

        return new String[] {matcher.group(1), matcher.group(2)};
    }

    public static String[] getEventDetails(String line) {
        String patternString = "^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            HerbertUI.printMessageInvalidInput();
            return null;
        }

        return new String[] {matcher.group(1), matcher.group(2), matcher.group(3)};
    }
}
