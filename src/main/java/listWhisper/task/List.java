package listWhisper.task;

import listWhisper.exceptions.DescriptionFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class List {
    ArrayList<Task> tasks;

    public List() {
        this.tasks = new ArrayList<Task>(100);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskId) {
        return this.tasks.get(taskId - 1);
    }

    @Override
    public String toString() {
        if (this.getSize() == 0) {
            return "You have no task in your list!\n";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(this.tasks.get(i - 1)).append("\n");
        }
        return output.toString();
    }

    //Actions
    public void addTodo(String description) throws DescriptionFormatException {
        if (description.equals("")) {
            throw new DescriptionFormatException(
                    "Wrong todo format. Follow this format to add a todo: "
                            + "todo [todo description]");
        }
        this.tasks.add(new Todo(description));
    }

    public void addDeadline(String description) throws DescriptionFormatException {
        try {
            String[] descriptionAndTime = splitInputIntoDeadlineFormat(description);
            this.tasks.add(new Deadline(descriptionAndTime[0], descriptionAndTime[1]));
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    public void addEvent(String description) throws DescriptionFormatException {
        try {
            String[] descriptionAndTime = splitInputIntoEventFormat(description);
            this.tasks.add(new Event(descriptionAndTime[0],
                    descriptionAndTime[1], descriptionAndTime[2]));
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    public void mark(String input) throws DescriptionFormatException {
        try {
            int taskId = getTaskId(input);
            this.tasks.get(taskId - 1).setDone(true);
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    public void unmark(String input) throws DescriptionFormatException {
        try {
            int taskId = getTaskId(input);
            this.tasks.get(taskId - 1).setDone(false);
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    public Task delete(String input) throws DescriptionFormatException {
        try {
            int taskId = getTaskId(input);
            Task task = this.tasks.get(taskId - 1);
            this.tasks.remove(taskId - 1);
            return task;
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    //Split input into different formats
    public String[] splitInputIntoDeadlineFormat(String description)
            throws DescriptionFormatException {
        String[] descriptionAndTime = description.split("/by");

        if (descriptionAndTime.length != 2) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to add a deadline: "
                        + "deadline [deadline description] /by[time and date of the deadline]");
        }
        return descriptionAndTime;
    }

    public  String[] splitInputIntoEventFormat(String description)
            throws DescriptionFormatException {
        String[] descriptionAndTime = description.split("/");

        if (descriptionAndTime.length != 3) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to add an event: " +
                            "event [event description] " +
                            "/from[start time and date] /to[end time and date]");
        }
        return descriptionAndTime;
    }

    private int getTaskId(String input) throws DescriptionFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 1) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to mark/unmark a task: "
                            + "mark/unmark [task id]");
        }
        return Integer.parseInt(splitInput[1]);
    }

}
