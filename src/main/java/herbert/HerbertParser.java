package herbert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains all functionality for parsing and validating user input.
 */
public abstract class HerbertParser {

    /**
     * Validates user input for commands which require exactly 1 extra argument to be input.
     * Used for commands such as `delete X` where X is a task index.
     * @param line The raw input string from the user.
     * @return 0 on success, -1 on an incorrect number of arguments input by the user.
     */
   public static int checkInputTaskIndex(String line) {
       if (line.split(" ").length != 2) {
           HerbertUI.printMessageInvalidInput(
                   "Please enter the task number you wish to change the status of."
           );
           return -1;
       }

       return 0;
   }

    /**
     * Extracts the integer task index from raw user input.
     * @param line The raw input string from the user.
     * @return The 0-indexed integer task index on success, -1 if the task index is not numeric.
     */
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

    /**
     * Verifies that a certain task index is valid against a given TaskList.
     * @param taskIndex The index of the task to validate.
     * @param taskList The Herbert TaskList to validate the index against.
     * @return 0 on success, -1 on invalid input.
     */
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

    /**
     * Validates user input upon adding a new task to Herbert.
     * The method checks whether at least 1 more argument has been supplied by the user.
     * @param line The raw input string from the user.
     * @return 0 on success, -1 on invalid input.
     */
    public static int checkInputTwoOrMoreArgs(String line) {
        String[] words = line.split(" ");
        if (words.length < 2) {
            HerbertUI.printMessageInvalidInput();
            return -1;
        }
        return 0;
    }

    /**
     * Runs a regex pattern matcher to extract details from user input related to a new deadline task.
     * @param line The raw input string from the user.
     * @return A string array of the form [description, due date].
     */
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

    /**
     * Runs a regex pattern matcher to extract details from user input related to a new event task.
     * @param line The raw input string from the user.
     * @return A string array of the form [description, from, to].
     */
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

    public static String getSearchQuery(String line) {
        String lower = line.toLowerCase();
        return lower.substring("find ".length());
    }
}
