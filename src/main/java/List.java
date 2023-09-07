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
        String[] splitInput = input.split(" ", 2);
        String action = splitInput[0];
        Task newTask= null;

        if (action.equals("todo")) {
            newTask = new Todo(splitInput[1]);
        } else if (action.equals("deadline")) {
            String[] command = splitInput[1].split("/by");
            newTask = new Deadline(command[0], command[1]);
        } else if (action.equals("event")) {
            String[] command = splitInput[1].split("/");
            newTask = new Event(command[0], command[1], command[2]);
        }
        this.tasks.add(newTask);
    }

    public void mark(int taskID) {
        this.tasks.get(taskID - 1).setDone(true);
    }

    public void unmark(int taskID) {
        this.tasks.get(taskID - 1).setDone(false);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(this.tasks.get(i - 1)).append("\n");
        }
        return output.toString();
    }
}
