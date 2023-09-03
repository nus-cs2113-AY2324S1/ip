import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    
    public static void printLine() {
        final String line = "__________________________________________";
        System.out.println(line);
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Chat0PT");
        System.out.println("What can I do for you?");
        printLine();
        ArrayList<Task> currentTask = new ArrayList<>();
        while(true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            printLine();
            switch(input) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                return;
            case "list":
                int i = 1;
                for (Task t: currentTask) {
                    if (t.isMarked()) {
                        System.out.println(i + ". [X] " + t.getTasks());
                    }
                    else {
                        System.out.println(i + ". [ ] " + t.getTasks());
                    }
                    i++;
                }
                printLine();
                break;
            default:
                String[] splitString = input.split(" ");
                if (splitString.length > 1) {
                    if (splitString[0].equals("mark")) {
                        int num = Integer.parseInt(splitString[1]) - 1;
                        if (num < currentTask.size()) {
                            currentTask.get(num).setMarked(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[X] " + currentTask.get(num).getTasks());
                        } else {
                            System.out.println("Are you sure that task exists?");
                        }
                        printLine();
                    } else if (splitString[0].equals("unmark")) {
                        int num = Integer.parseInt(splitString[1]) - 1;
                        if (num < currentTask.size()) {
                            currentTask.get(num).setMarked(false);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[ ] " + currentTask.get(num).getTasks());
                        } else {
                            System.out.println("Are you sure that task exists?");
                        }
                        printLine();
                    } else {
                        Task t = new Task(input);
                        currentTask.add(t);
                        System.out.println("added: " + input);
                        printLine();
                    }
                } else {
                    Task t = new Task(input);
                    currentTask.add(t);
                    System.out.println("added: " + input);
                    printLine();
                }
            }

        }


    }

}
