import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String prompt = "___________________________________\n"
                +  "Hello! I'm NOXIN \n"
                + "What can I do for you?\n"
                + "___________________________________\n";


        System.out.println(prompt);

        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ");
            if (command[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                Utils.printDivider();
                break;
            } else if (command[0].equals("list")) {
                toDoList.printList();
            } else if (command[0].equals("mark")) {
                toDoList.mark(Integer.parseInt(command[1]));
            } else if (command[0].equals("unmark")) {
                toDoList.unmark(Integer.parseInt(command[1]));
            }else {
                toDoList.addToList(input);
            }


        }

    }
}
