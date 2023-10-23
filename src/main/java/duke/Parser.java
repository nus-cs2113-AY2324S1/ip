package duke;

import duke.exceptions.NullValidInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

import static duke.Duke.addToFile;

/**
 * This class is designed to deal with making sense of the user command
 * e.g. analyze user input for different command like "todo", "event", "find" etc.
 */
public class Parser {
    /**
     * This is used to create an event object based on the user input,
     * and add this event into the taskList
     * @param taskList the list containing all the tasks for now where we should add our new event object into
     * @param line the user input
     * @throws NullValidInputException
     */
    public static void handleEvent(List<Task> taskList, String line) throws NullValidInputException {
        String[] userfulInfo = handleInputForEvent(line);
        Event event = new Event(userfulInfo[0], userfulInfo[1]);
        taskList.add(event);
        addToFile(taskList);
    }

    /**
     *  This is used to create a String array based on which we can create the event object
     * @param userInput the user input
     * @return
     * @throws NullValidInputException
     */
    public static String[] handleInputForEvent(String userInput) throws NullValidInputException {
        String usefulInput = userInput.replace("event", "").trim();
        String[] taskAndTimePeriod = usefulInput.split("/");
        if (taskAndTimePeriod.length < 3) {
            throw new NullValidInputException("Not enough input for event");
        }
        String description = taskAndTimePeriod[0];
        String timePeriod = taskAndTimePeriod[1] + taskAndTimePeriod[2];
        if (timePeriod.startsWith("from")) {
            timePeriod = "(" + timePeriod.replace("from", "from:").replace("to", "to:") + ")";
        } else {
            timePeriod = "(from: " + timePeriod.replace("to", "to:") + ")";
        }
        return new String[] {description, timePeriod};
    }

    /**
     * This is used to create a deadline object based on the user input,
     * and add this event into the taskList
     * @param taskList the list containing all the tasks for now where we should add our new deadline object into
     * @param line the user input
     */
    public static void handleDeadline(List<Task> taskList, String line) {
        String by = line.split("/")[1];
        String description = line.split("/")[0].replace("deadline", "").trim();
        Deadline deadline = new Deadline(description, by.replace("by", "").trim());
        taskList.add(deadline);
        addToFile(taskList);
    }

    /**
     * This is used to create a todo object based on the user input,
     * and add this event into the taskList
     * @param taskList the list containing all the tasks for now where we should add our new todo object into
     * @param words the String array derived by splitting the user input
     */
    public static void handleTodo(List<Task> taskList, String[] words) {
        String task = "";
        for (int i = 1; i < words.length; i++) {
            task += words[i] + " ";
        }
        Todo todo = new Todo(task.trim());
        taskList.add(todo);
        addToFile(taskList);

    }

    /**
     * This method is designed to handle the find command
     * we try to find if we have any tasks in our taskList whose description contains the keyword for this command
     * @param taskList the list containing all the tasks for now where we should scan based on the keyword
     * @param words the user input from which we can get the keyword for find command
     */
    public static void handleFind(List<Task> taskList, String[] words) {
        String keyword = "";
        for(int i = 1; i < words.length; i++) {
            keyword += words[i] + " ";
        }
        keyword = keyword.trim();

        List<Task> allWantedTask = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                allWantedTask.add(task);
            }

        }
        if (allWantedTask.size() == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("Sorry, you haven't saved any tasks regarding the given keyword yet.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < allWantedTask.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + allWantedTask.get(i));
            }
            System.out.println("____________________________________________________________");
        }
        addToFile(taskList);
    }

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("read book"));
        taskList.add(new Todo("a"));
        taskList.add(new Todo("c"));
        taskList.add(new Todo("return book"));
        String s = "find asddsa";
        handleFind(taskList, s.split(" "));
    }
}
