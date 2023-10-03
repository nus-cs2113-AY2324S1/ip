package jarvis.tasklist;

import jarvis.exception.JarvisException;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int EVENT_COMMAND_LENGTH = 6;
    private static final int DEADLINE_COMMAND_LENGTH = 9;
    private static final int BY_KEYWORD_LENGTH = 4;

    public static void showTodo(String description){
        Todo todo = new Todo(description);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + todo);
    }

    static String parseToDoDescription(String userInput) throws JarvisException {
        if (userInput.length() <= TODO_COMMAND_LENGTH) {
            throw JarvisException.invalidTodoFormat();
        }
        String description = userInput.split("todo")[1].strip();
        if(description.isEmpty()) {
            throw JarvisException.invalidTodoFormat();
        }
        return description;
    }

    public static void showDeadline(String description, String time){
        Deadline deadline = new Deadline(description, time);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + deadline);
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

    public static void showEvent(String description, String time){
        Event event = new Event(description, time);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + event);
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

        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw JarvisException.invalidEventFormat();
        }

        List<String> descriptionAndTime = new ArrayList<>();
        descriptionAndTime.add(description);
        String timeRange = startTime + " to " + endTime;
        descriptionAndTime.add(timeRange);

        return descriptionAndTime;
    }
}
