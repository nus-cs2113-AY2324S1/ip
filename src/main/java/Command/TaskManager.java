package Command;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

import java.util.ArrayList;
public class TaskManager {
    private static final int TODO_COMMAND_LENGTH = 5;
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

    public void markTaskAsDone(int index) throws JarvisException {
        if (isValidIndex(index)) {
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(index).markAsDone();
            System.out.println("    " + taskList.get(index));
        }
        else {
            throw JarvisException.invalidTaskNumber(index);
        }
    }

    public void unmarkTaskAsDone(int index) throws JarvisException {
        if (isValidIndex(index)) {
            System.out.println("Nice! I've unmarked:");
            taskList.get(index).markAsUndone();
            System.out.println("    " + taskList.get(index));
        }
        else {
            throw JarvisException.invalidTaskNumber(index);
        }
    }

    public void addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + todo);
        displayTaskCount();
    }

    static String parseToDoDescription(String userInput) throws JarvisException {
        if (userInput.length() <= 5) {
            throw JarvisException.invalidTodoFormat();
        }
        String description = userInput.substring(5).trim();
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

    static Deadline parseDeadlineDescription(String userInput) throws JarvisException {
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

        return new Deadline(description, time);
    }



    public void addEvent(String description, String startTime, String endTime) {
        String timeRange = startTime + " to " + endTime;
        Event event = new Event(description, timeRange);
        taskList.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + event);
        displayTaskCount();
    }

    private void displayTaskCount() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < taskList.size();
    }
}

