package Command;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

import java.util.ArrayList;
import java.util.List;
public class TaskManager {
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int EVENT_COMMAND_LENGTH = 6;

    private static final int DEADLINE_COMMAND_LENGTH = 9;
    private static final int BY_KEYWORD_LENGTH = 4;
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void listTasks() {
        System.out.println("Here's your tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + "." + taskList.get(i));
        }
    }

    public void markTaskAsDone(int index) throws JarvisException{
        try {
            if (isValidIndex(index)) {
                System.out.println("Nice! I've marked this task as done:");
                taskList.get(index).markAsDone();
                System.out.println("    " + taskList.get(index));
            }
            else {
                throw JarvisException.invalidTaskNumber(index);
            }
        } catch (JarvisException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unmarkTaskAsDone(int index) throws JarvisException{
        try {
            if (isValidIndex(index)) {
                System.out.println("I've unmarked this task:");
                taskList.get(index).markAsUndone();
                System.out.println("    " + taskList.get(index));
            }
            else {
                throw JarvisException.invalidTaskNumber(index);
            }
        } catch (JarvisException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int extractIndex(String userInput, int startIndex) {
        if (startIndex < userInput.length()) {
            return Integer.parseInt(userInput.substring(startIndex).trim()) - 1; // Convert to 0-indexed
        }
        else {
            return -1;
        }
    }

    public void addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        System.out.println(taskList);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + todo);
        displayTaskCount();
    }

    static String parseToDoDescription(String userInput) throws JarvisException {
        if (userInput.length() <= TODO_COMMAND_LENGTH) {
            throw JarvisException.invalidTodoFormat();
        }
        String description = userInput.substring(TODO_COMMAND_LENGTH).trim();
        if(description.isEmpty()) {
            throw JarvisException.invalidTodoFormat();
        }
        return description;
    }

    public void addDeadline(String description, String time) {
        Deadline deadline = new Deadline(description, time);
        taskList.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + deadline);
        displayTaskCount();
    }

    static List<String> parseDeadlineDescription(String userInput) throws JarvisException {
        int lastIndex = userInput.lastIndexOf("/by");
        if (lastIndex == -1) {
            throw JarvisException.invalidDeadlineFormat();
        }

        if (userInput.length() <= DEADLINE_COMMAND_LENGTH || lastIndex + BY_KEYWORD_LENGTH >= userInput.length()) {
            throw JarvisException.invalidDeadlineFormat();
        }
        String description = userInput.substring(DEADLINE_COMMAND_LENGTH, lastIndex).trim();
        String time = userInput.substring(lastIndex + BY_KEYWORD_LENGTH).trim();

        if(description.isEmpty() || time.isEmpty()) {
            throw JarvisException.invalidDeadlineFormat();
        }

        List<String> descriptionAndTime = new ArrayList<>();
        descriptionAndTime.add(description);
        descriptionAndTime.add(time);

        return descriptionAndTime;
    }

    public void addEvent(String description, String startTime, String endTime) {
        String timeRange = startTime + " to " + endTime;
        Event event = new Event(description, timeRange);
        taskList.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + event);
        displayTaskCount();
    }

    static List<String> parseEventDescription(String userInput) throws JarvisException {
        int lastIndexTo = userInput.lastIndexOf("/to");
        int lastIndexFrom = userInput.lastIndexOf("/from");
        if (lastIndexFrom == -1 && lastIndexTo == -1) {
            throw JarvisException.invalidEventFormat();
        }

        String[] parts = userInput.substring(EVENT_COMMAND_LENGTH).trim().split("/from|/to", 3);
        String description = parts[0].trim();
        String startTime = parts[1].trim();
        String endTime = parts[2].trim();

        List<String> descriptionAndTime = new ArrayList<>();
        descriptionAndTime.add(description);
        descriptionAndTime.add(startTime);
        descriptionAndTime.add(endTime);
        System.out.println((descriptionAndTime));

        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw JarvisException.invalidEventFormat();
        }
        return descriptionAndTime;
    }

    private void displayTaskCount() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < taskList.size();
    }
}