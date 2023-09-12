import java.util.ArrayList;

class List {
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
    public void add(String input) {
        String[] actionAndDescription = splitInputIntoActionAndDescription(input);
        String action = actionAndDescription[0];
        String description = actionAndDescription[1];
        String[] descriptionAndTime;
        Task newTask= null;

        switch (action) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            descriptionAndTime = description.split("/by");
            newTask = new Deadline(descriptionAndTime[0], descriptionAndTime[1]);
            break;
        case "event":
            descriptionAndTime = actionAndDescription[1].split("/");
            newTask = new Event(descriptionAndTime[0], descriptionAndTime[1], descriptionAndTime[2]);
            break;
        }
        this.tasks.add(newTask);
    }

    public String[] splitInputIntoActionAndDescription(String input) {
        return input.split(" ", 2);
    }

    public void mark(int taskID) {
        this.tasks.get(taskID - 1).setDone(true);
    }

    public void unmark(int taskID) {
        this.tasks.get(taskID - 1).setDone(false);
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
