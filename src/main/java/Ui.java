import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {




    public Ui(){
        System.out.println("    ____________________________________________________________");
        printLine("Hello! I'm Nupjuk");
        printLine("What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    /* print a line starts with four spaces */
    public void printLine(String s){
        System.out.println("     " + s);
    }

    public boolean runCommand(String cmd, TaskList tasks, Storage storage) throws IOException {
        Scanner input = new Scanner(System.in);

        String[] tokens = cmd.split(" ", 2);
        System.out.println("    ____________________________________________________________");
        //System.out.println(tokens[0]);
        if(cmd.equals("bye")){ // bye command
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
                return true;
            }
            printLine("Nice! I've marked this task as done:");
            printLine(" [" + tasks.getTask(mark_idx).getStatusIcon() + "] " + tasks.getTask(mark_idx).getDescription());
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks, tasks.getSize());

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
                tasks.getTask(mark_idx).doMark();
            } catch (IndexOutOfBoundsException e){
                printLine("☹ OOPS!!! Task number is out of List!");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            printLine("OK, I've marked this task as not done yet:");
            printLine(" [" + tasks.getTask(mark_idx).getStatusIcon() + "] " + tasks.getTask(mark_idx).getDescription());
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks, tasks.getSize());
        } else if(tokens[0].equals("todo")) {

            try{
                FormatChecker.checkInputFormat(tokens);
            } catch (InputFormatException e){
                printLine("☹ OOPS!!! <todo> should be with task description");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            // make and add to list
            printLine("Got it. I've added this task:");
            Todo todo = new Todo(tokens[1]);
            tasks.addTask(todo);

            printLine(String.format("  [%s][%s] %s",
                    todo.getTypeIcon(), todo.getStatusIcon(), todo.getDescription()));
            printLine(String.format("Now you have %d tasks in the list.", tasks.getSize()));
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks, tasks.getSize());

        } else if(tokens[0].equals("deadline")){
            // error handling
            try{
                FormatChecker.checkInputFormat(tokens);
            } catch (InputFormatException e){
                printLine("☹ OOPS!!! <deadline> should be with task description and deadline");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            // input format: description / deadline
            String[] schedules = tokens[1].split("/", 2);

            // error handling
            try{
                FormatChecker.checkDeadlineFormat(schedules);
            } catch (InputFormatException e) {
                printLine("☹ OOPS!!! <deadline> needs argument like (work/by time)");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm", Locale.ENGLISH);
            LocalDateTime date;
            // using datetime format
            try{
                date = LocalDateTime.parse(schedules[1].trim().substring(2).trim(), formatter);
            } catch (DateTimeParseException e){
                printLine("☹ OOPS!!! time must be this form: dd-MM-yyyy hhmm");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }


            // make and add to list
            printLine("Got it. I've added this task:");

            Deadline deadline = new Deadline(schedules[0].trim(), date);
            tasks.addTask(deadline);

            printLine(String.format("  [%s][%s] %s",
                    deadline.getTypeIcon(), deadline.getStatusIcon(), deadline.getDescription()));
            printLine(String.format("Now you have %d tasks in the list.", tasks.getSize()));
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks, tasks.getSize());
        } else if(tokens[0].equals("event")){
            // error handling
            try{
                FormatChecker.checkInputFormat(tokens);
            } catch (InputFormatException e){
                printLine("☹ OOPS!!! <event> should be with task description and start/end time");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            // input format: description / deadline
            String[] schedules = tokens[1].split("/", 3);

            // error handling
            try{
                FormatChecker.checkEventFormat(schedules);
            } catch (InputFormatException e) {
                printLine("☹ OOPS!!! <event> needs input like (work /from start /to end)");
                printLine("time must be this form: dd-MM-yyyy HHmm");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm", Locale.ENGLISH);
            LocalDateTime from;
            LocalDateTime to;
            // using datetime format
            try{
                from = LocalDateTime.parse(schedules[1].trim().substring(4).trim(), formatter);
                to = LocalDateTime.parse(schedules[2].trim().substring(2).trim(), formatter);
            } catch (DateTimeParseException e){
                printLine("☹ OOPS!!! time must be this form: dd-MM-yyyy HHmm");
                System.out.println("    ____________________________________________________________\n");
                return false;
            }

            // make and add to list
            printLine("Got it. I've added this task:");

            Event event = new Event(schedules[0].trim(), from, to);
            tasks.addTask(event);

            printLine(String.format("  [%s][%s] %s",
                    event.getTypeIcon(), event.getStatusIcon(), event.getDescription()));
            printLine(String.format("Now you have %d tasks in the list.", tasks.getSize()));
            System.out.println("    ____________________________________________________________\n");
            storage.saveTask(tasks, tasks.getSize());
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
            storage.saveTask(tasks, tasks.getSize());
        }
        else{
            // command not matched
            printLine("☹ Sorry, I cannot understand your command: " + cmd);
            System.out.println("    ____________________________________________________________\n");
        }
        return false;
    }

    public void exitProg(){
        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
