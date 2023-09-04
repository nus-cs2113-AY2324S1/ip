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
        String[] list = new String[100];
        int listSize = 0;

        while(true) {
            String input = scanner.nextLine();
            //for command bye
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if(input.equals("list")){
                for(int i = 0; i < listSize; i++) {
                    System.out.print((i+1) + ". ");
                    System.out.println(list[i]);
                }
            }else{
                //Echo input
                System.out.println("Added: " + input);
                System.out.println(line);
                //edit list array
                list[listSize] = input;
                listSize++;
            }
        }

        //close scanner
        scanner.close();
    }
}
