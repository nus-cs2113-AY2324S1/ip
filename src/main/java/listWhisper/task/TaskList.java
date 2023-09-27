package listWhisper.task;
import storage.DataManager;
import common.StringSplitter;
import listWhisper.exceptions.DescriptionFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskId) {
        return this.tasks.get(taskId - 1);
    }

    //Actions
    public Todo addTodo(String description) throws DescriptionFormatException {
        if (description.equals("")) {
            throw new DescriptionFormatException(
                    "Wrong todo format. Follow this format to add a todo: "
                            + "todo [todo description]");
        }
        Todo todo = new Todo(description);
        this.tasks.add(todo);
        return todo;
    }

    public Deadline addDeadline(String description) throws DescriptionFormatException {
        try {
            String[] descriptionAndTime = StringSplitter.splitInputIntoDeadlineFormat(description);
            Deadline deadline = new Deadline(descriptionAndTime[0], descriptionAndTime[1]);
            this.tasks.add(deadline);
            return deadline;
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    public Event addEvent(String description) throws DescriptionFormatException {
        try {
            String[] descriptionAndTime = StringSplitter.splitInputIntoEventFormat(description);
            Event event = new Event(descriptionAndTime[0],
                    descriptionAndTime[1], descriptionAndTime[2]);
            this.tasks.add(event);
            return event;
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    public Task mark(String input) throws DescriptionFormatException {
        try {
            int taskId = getTaskId(input);
            Task task = this.tasks.get(taskId - 1);
            task.setDone(true);
            return task;
        } catch (DescriptionFormatException e) {
            throw e;
        }
    }

    public Task unmark(String input) throws DescriptionFormatException {
        try {
            int taskId = getTaskId(input);
            this.tasks.get(taskId - 1).setDone(false);
            return this.tasks.get(taskId - 1);
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

    // Methods related to data management
    public void load(ArrayList<String> data) throws DescriptionFormatException {
        for (String line : data) {
            TaskClassifier.classifyTaskForLoading(this, line);
        }
    }

    public void saveList() throws IOException {
        StringBuilder listOfFormattedTasks = new StringBuilder();
        for (Task task : this.tasks) {
            listOfFormattedTasks.append(task.formatAsInput()).append("\n");
        }
        DataManager.saveList(listOfFormattedTasks.toString());
    }

    // Miscellaneous methods
    private int getTaskId(String input) throws DescriptionFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 1) {
            throw new DescriptionFormatException(
                    "Wrong input format. Follow this format to mark/unmark a task: "
                            + "mark/unmark [task id]");
        }
        return Integer.parseInt(splitInput[1]);
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
}
