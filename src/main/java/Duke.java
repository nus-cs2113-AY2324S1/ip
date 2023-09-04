//import scanner
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        //message
        System.out.println("Hello! I'm Botbot");
        System.out.println("What can I do for you?");
        System.out.println(line);

        //scan input
        Scanner scanner = new Scanner(System.in);

        //create array for list
        Task[] list = new Task[100];
        int listSize = 0;

        while(true) {
            String input = scanner.nextLine();
            //for command bye
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if(input.equals("list")) {
                for (int i = 0; i < listSize; i++) {
                    System.out.print((i + 1) + ". ");
                    list[i].printTask();
                }
                System.out.println(line);
            }else if(input.contains("mark")){
                int itemIndex;
                if(input.contains("unmark")){
                    itemIndex = Integer.parseInt(input.substring(7))-1;
                    list[itemIndex].unmark();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    list[itemIndex].printTask();
                    System.out.println(line);
                } else {
                    itemIndex = Integer.parseInt(input.substring(5))-1;
                    list[itemIndex].mark();
                    System.out.println("Nice! I've marked this task as done: ");
                    list[itemIndex].printTask();
                    System.out.println(line);
                }
            }else{
                //instantiate new Task object
                Task newTask = new Task(input);
                //Echo input
                System.out.println("Added: " + input);
                System.out.println(line);
                //edit list array
                list[listSize] = newTask;
                listSize++;
            }
        }

        //close scanner
        scanner.close();
    }
}
