package nupjuk;

import nupjuk.command.DeadlineCommand;
import nupjuk.command.EventCommand;
import nupjuk.command.TodoCommand;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static nupjuk.Printer.printLine;


/**
 * Shows the User Interface
 * Print things in specific format
 */

public class Ui {
    public Ui(){
        System.out.println("    ____________________________________________________________");
        printLine("Hello! I'm nupjuk.Nupjuk");
        printLine("What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }




    /**
     * run the user-input commmand
     * If user inputs "bye", returns true to terminate infinite loop of run() function
     * Otherwise, returns false
     *
     * @param cmd nupjuk.command string that user inputted
     * @param tasks List of Todos/Deadlines/Events
     * @param storage Where to save and load
     * @return terminates or not
     * @throws IOException if problem in input and parsing
     */
    public boolean runCommand(String cmd, TaskList tasks, Storage storage) throws IOException {
        Scanner input = new Scanner(System.in);

        String[] tokens = cmd.split(" ", 2);
        System.out.println("    ____________________________________________________________");
        //System.out.println(tokens[0]);
        if(cmd.equals("bye")){ // bye nupjuk.command
            return true;
        } else if(cmd.equals("list")){
            if(tasks.getSize() == 0){
                printLine("Nothing in the list");
            }
            else{
                for(int i=0;i<tasks.getSize();i++){
                    Task task = tasks.getTask(i);
                    printLine(String.format("%d.[%s][%s] %s",
                            i+1, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
                }
            }
            System.out.println("    ____________________________________________________________\n");
        } else if(tokens[0].equals("mark")){
            int mark_idx;

            try {
                mark_idx = Integer.parseInt(tokens[1]) - 1; // index starts from 0
            } catch (ArrayIndexOutOfBoundsException e){
                printLine("☹ OOPS!!! <mark> needs one integer parameter!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            } catch (NumberFormatException e) {
                printLine("☹ OOPS!!! Task number should be one integer!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            try {
                tasks.getTask(mark_idx).doMark();
            } catch (IndexOutOfBoundsException e){
                printLine("☹ OOPS!!! Task number is out of List!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }
            printLine("Nice! I've marked this task as done:");
            printLine(" [" + tasks.getTask(mark_idx).getStatusIcon() + "] " + tasks.getTask(mark_idx).getDescription());
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks);

        } else if(tokens[0].equals("unmark")){

            int mark_idx;

            try {
                mark_idx = Integer.parseInt(tokens[1]) - 1; // index starts from 0
            } catch (ArrayIndexOutOfBoundsException e){
                printLine("☹ OOPS!!! <unmark> needs one integer parameter!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            } catch (NumberFormatException e) {
                printLine("☹ OOPS!!! Task number should be one integer!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            try {
                tasks.getTask(mark_idx).unMark();
            } catch (IndexOutOfBoundsException e){
                printLine("☹ OOPS!!! Task number is out of List!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            printLine("OK, I've marked this task as not done yet:");
            printLine(" [" + tasks.getTask(mark_idx).getStatusIcon() + "] " + tasks.getTask(mark_idx).getDescription());
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks);
        } else if(tokens[0].equals("todo")) {
            TodoCommand command = new TodoCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("deadline")){
            DeadlineCommand command = new DeadlineCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("event")){
            EventCommand command = new EventCommand();
            return command.execute(tasks, tokens, storage);
        } else if(tokens[0].equals("delete")){

            int del_idx;

            try {
                del_idx = Integer.parseInt(tokens[1]) - 1; // index starts from 0
            } catch (ArrayIndexOutOfBoundsException e){
                printLine("☹ OOPS!!! <delete> needs one integer parameter!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            } catch (NumberFormatException e) {
                printLine("☹ OOPS!!! Task number should be one integer!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            Task del_task;

            try {
                del_task = tasks.getTask(del_idx);
                tasks.removeTask(del_idx);
            } catch (IndexOutOfBoundsException e){
                printLine("☹ OOPS!!! Task number is out of List!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            printLine("Noted. I've removed this task:");
            printLine(" [" + del_task.getStatusIcon() + "] " + del_task.getDescription());
            printLine(String.format("now you have %d tasks in the list", tasks.getSize()));
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks);
        } else if(tokens[0].equals("find")){
            int foundTasks = 0;
            for(int i=0;i<tasks.getSize();i++){
                Task task = tasks.getTask(i);
                String description = task.getDescription();
                if(description.contains(tokens[1].trim())){
                    foundTasks++;
                    if(foundTasks == 1){
                        printLine("Here are the matching tasks in your list:");
                    }
                    printLine(String.format("%d.[%s][%s] %s",
                            foundTasks, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
                }
            }
            if(foundTasks == 0){
                printLine("Cannot found any matching Tasks");
            }
            System.out.println("    ____________________________________________________________\n");
        } else{
            // nupjuk.command not matched
            printLine("☹ Sorry, I cannot understand your nupjuk.command: " + cmd);
            System.out.println("    ____________________________________________________________\n");
        }
        return false;
    }


    /**
     * Prints a termination message.
     */
    public void exitProg(){
        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
