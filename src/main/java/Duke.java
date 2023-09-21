import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    /* print a line starts with four spaces*/
    static void printLine(String s){
        System.out.println("     " + s);
    }

    /* simple function that returns if the string is numeric*/
    static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /* function for check input format */
    static void checkInputFormat(String[] input) throws InputFormatException{
        if(input.length !=2){
            throw new InputFormatException();
        }
    }

    /* function for check deadline input format*/
    static void checkDeadlineFormat(String[] schedules) throws InputFormatException{
        if(schedules.length !=2 || !schedules[1].trim().startsWith("by")){
            throw new InputFormatException();
        }
    }

    static void checkEventFormat(String[] schedules) throws InputFormatException{
        if(schedules.length !=3 ||
                !schedules[1].trim().startsWith("from") ||
                !schedules[2].trim().startsWith("to")){
            throw new InputFormatException();
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<>();
        int tasks_size = 0;

        System.out.println("    ____________________________________________________________");
        printLine("Hello! I'm Nupjuk");
        printLine("What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        while(true){ // get input until bye
            String cmd = input.nextLine();
            String[] tokens = cmd.split(" ", 2);
            System.out.println("    ____________________________________________________________");
            //System.out.println(tokens[0]);
            if(cmd.equals("bye")){ // bye command
                break;
            } else if(cmd.equals("list")){
                if(tasks_size == 0){
                    printLine("Nothing in the list");
                }
                else{
                    for(int i=0;i<tasks_size;i++){
                        Task task = tasks.get(i);
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
                    continue;
                } catch (NumberFormatException e) {
                    printLine("☹ OOPS!!! Task number should be one integer!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                try {
                    tasks.get(mark_idx).doMark();
                } catch (IndexOutOfBoundsException e){
                    printLine("☹ OOPS!!! Task number is out of List!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }
                printLine("Nice! I've marked this task as done:");
                printLine(" [" + tasks.get(mark_idx).getStatusIcon() + "] " + tasks.get(mark_idx).getDescription());
                System.out.println("    ____________________________________________________________\n");

            } else if(tokens[0].equals("unmark")){

                int mark_idx;

                try {
                    mark_idx = Integer.parseInt(tokens[1]) - 1; // index starts from 0
                } catch (ArrayIndexOutOfBoundsException e){
                    printLine("☹ OOPS!!! <unmark> needs one integer parameter!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                } catch (NumberFormatException e) {
                    printLine("☹ OOPS!!! Task number should be one integer!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                try {
                    tasks.get(mark_idx).doMark();
                } catch (IndexOutOfBoundsException e){
                    printLine("☹ OOPS!!! Task number is out of List!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                printLine("OK, I've marked this task as not done yet:");
                printLine(" [" + tasks.get(mark_idx).getStatusIcon() + "] " + tasks.get(mark_idx).getDescription());
                System.out.println("    ____________________________________________________________\n");
            } else if(tokens[0].equals("todo")) {

                try{
                    checkInputFormat(tokens);
                } catch (InputFormatException e){
                    printLine("☹ OOPS!!! <todo> should be with task description");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // make and add to list
                printLine("Got it. I've added this task:");
                Todo todo = new Todo(tokens[1]);
                tasks.add(todo);
                tasks_size++;

                printLine(String.format("  [%s][%s] %s",
                        todo.getTypeIcon(), todo.getStatusIcon(), todo.getDescription()));
                printLine(String.format("Now you have %d tasks in the list.", tasks_size));
                System.out.println("    ____________________________________________________________\n");

            } else if(tokens[0].equals("deadline")){
                // error handling
                try{
                    checkInputFormat(tokens);
                } catch (InputFormatException e){
                    printLine("☹ OOPS!!! <deadline> should be with task description and deadline");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // input format: description / deadline
                String[] schedules = tokens[1].split("/", 2);

                // error handling
                try{
                    checkDeadlineFormat(schedules);
                } catch (InputFormatException e) {
                    printLine("☹ OOPS!!! <deadline> needs argument like (work/by time)");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // make and add to list
                printLine("Got it. I've added this task:");

                Deadline deadline = new Deadline(schedules[0].trim(), schedules[1].trim().substring(2).trim());
                tasks.add(deadline);
                tasks_size++;

                printLine(String.format("  [%s][%s] %s",
                        deadline.getTypeIcon(), deadline.getStatusIcon(), deadline.getDescription()));
                printLine(String.format("Now you have %d tasks in the list.", tasks_size));
                System.out.println("    ____________________________________________________________\n");
            } else if(tokens[0].equals("event")){
                // error handling
                try{
                    checkInputFormat(tokens);
                } catch (InputFormatException e){
                    printLine("☹ OOPS!!! <event> should be with task description and start/end time");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // input format: description / deadline
                String[] schedules = tokens[1].split("/", 3);

                // error handling
                try{
                    checkEventFormat(schedules);
                } catch (InputFormatException e) {
                    printLine("☹ OOPS!!! <event> needs input like (work /from start /to end)");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // make and add to list
                printLine("Got it. I've added this task:");

                Event event = new Event(schedules[0].trim(),
                        schedules[1].trim().substring(4).trim(),
                        schedules[2].trim().substring(2).trim());
                tasks.add(event);
                tasks_size++;

                printLine(String.format("  [%s][%s] %s",
                        event.getTypeIcon(), event.getStatusIcon(), event.getDescription()));
                printLine(String.format("Now you have %d tasks in the list.", tasks_size));
                System.out.println("    ____________________________________________________________\n");
            } else if(tokens[0].equals("delete")){

                int del_idx;

                try {
                    del_idx = Integer.parseInt(tokens[1]) - 1; // index starts from 0
                } catch (ArrayIndexOutOfBoundsException e){
                    printLine("☹ OOPS!!! <delete> needs one integer parameter!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                } catch (NumberFormatException e) {
                    printLine("☹ OOPS!!! Task number should be one integer!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                Task del_task;

                try {
                    del_task = tasks.get(del_idx);
                    tasks.remove(del_idx);
                    tasks_size--;
                } catch (IndexOutOfBoundsException e){
                    printLine("☹ OOPS!!! Task number is out of List!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                printLine("Noted. I've removed this task:");
                printLine(" [" + del_task.getStatusIcon() + "] " + del_task.getDescription());
                printLine(String.format("now you have %d tasks in the list", tasks_size));
                System.out.println("    ____________________________________________________________\n");
            }
            else{
                // command not matched
                printLine("☹ Sorry, I cannot understand your command: " + cmd);
                System.out.println("    ____________________________________________________________\n");
            }
        }

        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
