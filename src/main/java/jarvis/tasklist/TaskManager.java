package jarvis.tasklist;

import jarvis.exception.JarvisException;
import jarvis.tasks.Todo;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int EVENT_COMMAND_LENGTH = 6;
    private static final int DEADLINE_COMMAND_LENGTH = 9;
    private static final int BY_KEYWORD_LENGTH = 4;
    /**
     * Displays the newly added Todo task to the user.
     *
     * @param description The description of the Todo task.
     */
    public static void showTodo(String description){
        Todo todo = new Todo(description);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + todo);
    }
    /**
     * Parses the user input to extract the description for a Todo task.
     *
     * @param userInput The user's input as a string.
     * @return The extracted description as a string.
     * @throws JarvisException If the extracted description is invalid.
     */
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
    /**
     * Displays the newly added Deadline task to the user.
     *
     * @param description The description of the Deadline task.
     * @param time The deadline time.
     */
    public static void showDeadline(String description, LocalDateTime time){
        Deadline deadline = new Deadline(description, time);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + deadline);
    }
    /**
     * Parses the user input to extract the description and time for a Deadline task.
     *
     * @param userInput The user's input as a string.
     * @return A list containing the extracted description and time.
     * @throws JarvisException If the extracted description or time is invalid.
     */
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
    /**
     * Displays the newly added Event task to the user.
     *
     * @param description The description of the Event task.
     * @param startDateTime The starting date and time of the event.
     * @param endDateTime The ending date and time of the event.
     */
    public static void showEvent(String description, LocalDateTime startDateTime, LocalDateTime endDateTime){
        Event event = new Event(description, startDateTime, endDateTime);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + event);
    }
    /**
     * Parses the user input to extract the description, start time, and end time for an Event task.
     *
     * @param userInput The user's input as a string.
     * @return A list containing the extracted description, start time, and end time.
     * @throws JarvisException If the extracted information is invalid.
     */
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
