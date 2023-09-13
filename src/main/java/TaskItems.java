import java.util.Scanner;

public class TaskItems {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________";

    public static final String MARK_TASK_COMMAND = "mark ";
    public static final String UNMARK_TASK_COMMAND = "unmark ";
    public static final String LIST_TASK_COMMAND = "list";
    public static final String EXIT_BOT_COMMAND = "bye";
    public static final String TODO_TASK_COMMAND = "todo ";
    public static final String DEADLINE_TASK_COMMAND = "deadline ";
    public static final String DEADLINE_DATE_COMMAND = "/by ";
    public static final String EVENT_TASK_COMMAND = "event ";
    public static final String EVENT_TASK_START = "/from ";
    public static final String EVENT_TASK_END = "/to ";

    //to echo after a new task is added
    public static void echo(Task task){
        System.out.printf((outputFormat) + "%n", "New task added: " + task.getDescription() + "\n" +
                "    Type 'list' to view all your tasks :D");
    }
    //to echo after a task's status is changed
    public static void echo(Task task, boolean isDone){
        String output = isDone ? "Congrats! :D " + "Task marked as done: " + task.getDescription() + " [" + task.getStatusIcon() + "]"
                : "Oopsies!" + "Task unmarked: " + task.getDescription() + " [" + task.getStatusIcon() + "]";
        System.out.printf(outputFormat + "%n", output);
    }

    //to echo when LIST command is being used
    public static void echo(Task[] items){
        System.out.println("    ____________________________________________________________");
        int index=0;
        for(Task item : items){
            if((item != null)) {
                System.out.print("    "+ ++index + ". ");
                System.out.println(item.toString());
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    //The function 'addItems' is used to accept user's input and add it to their To Do list
    public static void addItems() {
        Scanner in = new Scanner(System.in);
        String input = "";

        Task[] items = new Task[100];
        int index = 0;
        Task task = null;

        while(!(input = in.nextLine()).equals(EXIT_BOT_COMMAND)){
            if(input.startsWith(MARK_TASK_COMMAND)) {
                task = items[Integer.parseInt(input.substring(MARK_TASK_COMMAND.length())) - 1];
                task.setAsDone();
                echo(task, task.isDone);
            } else if(input.startsWith(UNMARK_TASK_COMMAND)) {
                task = items[Integer.parseInt(input.substring(UNMARK_TASK_COMMAND.length())) - 1];
                task.setAsNotDone();
                echo(task, task.isDone);
            } else if(input.equals(LIST_TASK_COMMAND)){
                echo(items);
            }
            else {
                task = addTaskByType(input);
                items[index++] = task;
                echo(task);
            }
        }
        System.out.printf((outputFormat) + "%n", "Goodbye <3 have a great day ahead!");
    }

    // The function 'addTaskByType' is used to add an item into the task list by their task type
    public static Task addTaskByType(String input){
        Task task = null;
        if(input.startsWith(TODO_TASK_COMMAND)) {
            task = new ToDos(input.substring(TODO_TASK_COMMAND.length()));
        } else if(input.startsWith(DEADLINE_TASK_COMMAND)){
            int byIndex = input.indexOf(DEADLINE_DATE_COMMAND);
            String description = input.substring(DEADLINE_TASK_COMMAND.length(), byIndex).trim();
            String by = input.substring(byIndex + DEADLINE_DATE_COMMAND.length()).trim();
            task = new Deadline(description, by);
        } else if (input.startsWith(EVENT_TASK_COMMAND)) {
            int fromIndex = input.indexOf(EVENT_TASK_START);
            int toIndex = input.indexOf(EVENT_TASK_END);
            String description = input.substring(EVENT_TASK_COMMAND.length(), fromIndex).trim();
            String from = input.substring(fromIndex + EVENT_TASK_START.length(), toIndex).trim();
            String to = input.substring(toIndex + EVENT_TASK_END.length()).trim();
            task = new Event(description, from, to);
        } else{
            //throw exception
            System.out.println("oh nos wrong command");
        }
        return task;
    }

}
