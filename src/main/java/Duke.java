import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        Task[] theList = new Task[100];
        Scanner in = new Scanner(System.in);
        int i = 0;

        String echo = in.nextLine();

        while (!echo.equals("bye")) {
            String[] words = echo.split(" "); //to identify usage of features "mark" & "unmark"
            if (echo.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j += 1) {
                    System.out.print((j + 1) + ".");
                    theList[j].printTask();
                }
            } else if (words[0].equals("mark")) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                System.out.println("Woohoo! You have accomplished:");
                theList[taskNumber].markAsDone();
                theList[taskNumber].printTask();
            } else if (words[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                System.out.println("HA! You still have to complete:");
                theList[taskNumber].markAsIncomplete();
                theList[taskNumber].printTask();
            } else {
                theList[i] = new Task(echo);
                System.out.println("added: " + echo);
                i += 1;
            }
            echo = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        
        in.close();
    }
}
