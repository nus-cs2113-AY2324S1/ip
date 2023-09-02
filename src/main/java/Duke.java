import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void drawLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void hiDude() {
        String logo = "###            #        \n"
                + "#  #           #        \n"
                + "#  #  #  #   ###   ##   \n"
                + "#  #  #  #  #  #  # ##  \n"
                + "#  #  #  #  #  #  ##    \n"
                + "###    ###   ###   ##   \n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm your best Dude :)");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void storeDude() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();
        int curPos = 0;

        while (!(input.isEmpty())) {
            drawLine();
            if (input.equals("bye")) {
                byeDude();
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < curPos; i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).getStatusIcon() + " " + tasks.get(i).description);
                }
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                String[] arrOfInput = input.split(" ", 2);
                if (arrOfInput.length < 2) {
                    System.out.println("Please specify the task index.");
                } else {
                    try {
                        int index = Integer.parseInt(arrOfInput[1]) - 1;
                        if (index < 0 || index >= curPos) {
                            System.out.println("Task index out of range.");
                        } else {
                            if (input.startsWith("mark")) {
                                tasks.get(index).isDone = true;
                                System.out.println("Nice! I've marked this task as done:");
                            } else {
                                tasks.get(index).isDone = false;
                                System.out.println("OK, I've marked this task as not done yet:");
                            }
                            System.out.println("   " + tasks.get(index).getStatusIcon() + " " + tasks.get(index).description);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task index format.");
                    }
                }
            } else {
                System.out.println("added: " + input);
                tasks.add(new Task(input));
                curPos++;
            }
            drawLine();
            input = scan.nextLine();
        }
        scan.close();
    }

    public static void byeDude() {
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void main(String[] args) {
        hiDude();
        storeDude();
    }
}
