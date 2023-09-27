package taskmanagement;


import Commands.Commands;
import exceptions.ZranExceptions;
import userinputs.Parser;
import userinputs.Ui;

import java.io.IOException;
import java.util.ArrayList;


public class TaskList {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________ ";

    public static final String DELETE_TASK_COMMAND = "delete ";

    //to echo after a new task is added
    public static void echo(ArrayList<Task> items, Task task, String input){
        String output = input.startsWith(DELETE_TASK_COMMAND)? "Noted!" + "Task deleted: " + task.getDescription() + "\n" +
                "    Number of Tasks: " + items.size()
                : "Noted!" + "Task added: " + task.getDescription() + "\n" +
                "    Number of Tasks: " + items.size() ;
        System.out.printf(outputFormat + "%n", output);
        echo(items);
    }
    //to echo after a task's status is changed
    public static void echo(Task task, boolean isDone){
        String output = isDone ? "Congrats! :D " + "Task marked as done: " + task.getDescription() + " [" + task.getStatusIcon() + "]"
                : "Oopsies!" + "Task unmarked: " + task.getDescription() + " [" + task.getStatusIcon() + "]";
        System.out.printf(outputFormat + "%n", output);
    }

    //to echo when LIST command is being used
    public static void echo(ArrayList<Task> items){
        System.out.println("    ____________________________________________________________");
        System.out.println("    List of Tasks:");
        int index=0;
        for(Task item : items){
            if((item != null)) {
                System.out.print("    "+ ++index + ". ");
                System.out.println(item.toString());
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void addItems(Ui ui) throws ZranExceptions, IOException {
        ArrayList<Task> items = Storage.loadTaskData();
        Task task = null;

        while (true) {
            String input = ui.readCommand();

            if (input.equals(Ui.EXIT_BOT_COMMAND)) {
                break; // Exit the loop when the user types 'bye'
            }

            Commands command = Parser.parse(input);
            if (command != null) {
                command.execute(items, ui, null); // Pass null as storage, or provide a storage object if needed
            } else {
                ui.showError("Invalid command. Type 'help' to view the list of commands.");
            }
        }

        Storage.saveTasks(items);
        ui.showGoodbye();
    }

}
