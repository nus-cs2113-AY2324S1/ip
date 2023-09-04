import java.util.ArrayList;

class List {
    ArrayList<Task> tasks;

    public List() {
        this.tasks = new ArrayList<Task>(100);
    }

    public void add(String input) {
        String[] splitInput = input.split(" ", 2);
        String action = splitInput[0];
        Task newTask= null;

        System.out.println("Got it. I've added this task:");
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
        System.out.println(newTask);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));

    }

    public void mark(int taskID) {
        this.tasks.get(taskID - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(taskID - 1));
    }

    public void unmark(int taskID) {
        this.tasks.get(taskID - 1).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(taskID - 1));
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(this.tasks.get(i - 1)).append("\n");
        }
        return output.toString();
    }
}
