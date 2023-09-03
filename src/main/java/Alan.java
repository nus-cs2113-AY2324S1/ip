import java.util.Scanner;

public class Alan {

    public static void printGreet() {
        printHorizontalLine();
        String man = " @/\n" +
                     "/| \n" +
                     "/ \\";

        System.out.println(man);
        System.out.println("Hello! I'm Alan");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        Task[] taskList = new Task[101];
        int currentTaskListIndex = 1;

        printGreet();

        String userInput = " ";
        Scanner in = new Scanner(System.in);

        while(!userInput.equals("bye")) {
            //Read user input
            System.out.print("Input: ");
            userInput = in.nextLine();

            printHorizontalLine();

            if (userInput.equals("list")) {     //print the tasks in the lists
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < currentTaskListIndex; i++) {
                    System.out.println((i) + ". [" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
                }
            } else if (userInput.startsWith("mark")) {  //mark tasks as done
                String[] words = userInput.split(" ");

                taskList[Integer.parseInt(words[1])].setDone(true);

                System.out.println("Alright! I've marked this task as done:");
                System.out.println("\t[" + taskList[Integer.parseInt(words[1])].getStatusIcon() + "] " +
                        taskList[Integer.parseInt(words[1])].getDescription());

            } else if (userInput.startsWith("unmark")) { //unmark tasks as undone
                String[] words = userInput.split(" ");

                taskList[Integer.parseInt(words[1])].setDone(false);

                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("\t[" + taskList[Integer.parseInt(words[1])].getStatusIcon() + "] " +
                        taskList[Integer.parseInt(words[1])].getDescription());

            } else {    //add a new task to the list
                Task task = new Task(userInput);
                taskList[currentTaskListIndex] = task;
                currentTaskListIndex++;
                System.out.println("added: " + userInput);
            }
            printHorizontalLine();
        }

        printExit();
    }
}
