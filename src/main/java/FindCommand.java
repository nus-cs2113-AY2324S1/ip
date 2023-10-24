import java.util.ArrayList;

public class FindCommand {
    /**
     * Finds tasks containing a specified keyword and returns them in a list.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of matching tasks.
     */
    public static ArrayList<String> findTasksByKeyword(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < TaskList.taskDescriptions.size(); i++) {
            String description = TaskList.taskDescriptions.get(i);
            if (description.contains(keyword)) {
                String matchingTask = "[" + TaskList.taskTypes.get(i) + "]" + TaskList.taskToString(i);
                matchingTasks.add((i + 1) + ". " + matchingTask); // Include the task number
            }
        }
        return matchingTasks;
    }

    /**
     * Handles the "find" command, searching for tasks with a specified keyword.
     *
     * @param userInput The user input string containing the "find" command and keyword.
     */
    public static void handleFindCommand(String userInput) {
        String keyword = userInput.substring(CommandParser.COMMAND_FIND.length()).trim();
        ArrayList<String> matchingTasks = findTasksByKeyword(keyword);

        Ui.printLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (String matchingTask : matchingTasks) {
                System.out.println(matchingTask);
            }
        }
        Ui.printLine();
    }
}


