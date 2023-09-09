import java.util.Scanner;

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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
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
                        printLine(String.format("%d.[%s][%s] %s",
                                i+1, tasks[i].getTypeIcon(), tasks[i].getStatusIcon(), tasks[i].getDescription()));
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
                    tasks[mark_idx].doMark();
                } catch (NullPointerException e){
                    printLine("☹ OOPS!!! Task number is out of List!");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }
                printLine("Nice! I've marked this task as done:");
                printLine(" [" + tasks[mark_idx].getStatusIcon() + "] " + tasks[mark_idx].getDescription());
                System.out.println("    ____________________________________________________________\n");

            } else if(tokens[0].equals("unmark")){
                if(tokens.length !=2 || !isNumeric(tokens[1])){
                    printLine("unexpected behavior: 'mark' needs one integer parameter");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                printLine("OK, I've marked this task as not done yet:");
                int mark_idx = Integer.parseInt(tokens[1])-1; // index starts from 0

                // if idx not in proper range
                if(mark_idx <0 || mark_idx >= tasks_size){
                    printLine("unexpected behavior: index out of range");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // unmark
                tasks[mark_idx].unMark();
                printLine(" [" + tasks[mark_idx].getStatusIcon() + "] " + tasks[mark_idx].getDescription());
                System.out.println("    ____________________________________________________________\n");
            } else if(tokens[0].equals("todo")) {
                // error handling
                if (tokens.length != 2) {
                    printLine("unexpected behavior: 'todo' needs more string");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // make and add to list
                printLine("Got it. I've added this task:");
                Todo todo = new Todo(tokens[1]);
                tasks[tasks_size++] = todo;

                printLine(String.format("  [%s][%s] %s",
                        todo.getTypeIcon(), todo.getStatusIcon(), todo.getDescription()));
                printLine(String.format("Now you have %d tasks in the list.", tasks_size));
                System.out.println("    ____________________________________________________________\n");

            } else if(tokens[0].equals("deadline")){
                // error handling
                if (tokens.length != 2) {
                    printLine("unexpected behavior: 'deadline' needs more string");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // input format: description / deadline
                String[] schedules = tokens[1].split("/", 2);

                // error handling
                if(schedules.length !=2 || !schedules[1].trim().startsWith("by")){
                    printLine("unexpected behavior: 'deadline' needs input like (work/by time)");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // make and add to list
                printLine("Got it. I've added this task:");

                Deadline deadline = new Deadline(schedules[0].trim(), schedules[1].trim().substring(2).trim());
                tasks[tasks_size++] = deadline;

                printLine(String.format("  [%s][%s] %s",
                        deadline.getTypeIcon(), deadline.getStatusIcon(), deadline.getDescription()));
                printLine(String.format("Now you have %d tasks in the list.", tasks_size));
                System.out.println("    ____________________________________________________________\n");
            } else if(tokens[0].equals("event")){
                // error handling
                if (tokens.length != 2) {
                    printLine("unexpected behavior: 'event' needs more string");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // input format: description / deadline
                String[] schedules = tokens[1].split("/", 3);

                // error handling
                if(schedules.length !=3 ||
                        !schedules[1].trim().startsWith("from") ||
                        !schedules[2].trim().startsWith("to")){
                    printLine("unexpected behavior: 'event' needs input like (work/by time)");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // make and add to list
                printLine("Got it. I've added this task:");

                Event event = new Event(schedules[0].trim(),
                        schedules[1].trim().substring(4).trim(),
                        schedules[2].trim().substring(2).trim());
                tasks[tasks_size++] = event;

                printLine(String.format("  [%s][%s] %s",
                        event.getTypeIcon(), event.getStatusIcon(), event.getDescription()));
                printLine(String.format("Now you have %d tasks in the list.", tasks_size));
                System.out.println("    ____________________________________________________________\n");
            } else{
                printLine("added: " + cmd); // add list
                tasks[tasks_size++] = new Task(cmd);
                System.out.println("    ____________________________________________________________\n");
            }
        }

        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
