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
            } else if (command[0].equals("todo")) {
                ToDos toDo = new ToDos(input.substring(5)); //read whatever is behind "todo "
                toDoList.addToList("T", toDo);
            } else if (command[0].equals("deadline")) {
                String[] parts = input.split("/by"); // store the input into different array elements split by "/by"
                String deadlineName = parts[0].substring(9).trim(); //store name by reading what is behind "deadline "
                String due = parts[1].trim();
                Deadlines deadline = new Deadlines(deadlineName, due);
                toDoList.addToList("D", deadline);
            } else if (command[0].equals("event")) {
                String[] parts = input.split(("/from")); //store input into different elements by split "/from"
                String eventName = parts[0].substring(6).trim(); //store event name by reading what is behind "event "
                String[] timeFromTimeTo = parts[1].split("/to"); // split the time into time from and time to
                String timeFrom = timeFromTimeTo[0].trim();
                String timeTo = timeFromTimeTo[1].trim();
                Events event = new Events(eventName, timeFrom, timeTo);
                toDoList.addToList("E", event);
            } else {
                Task task = new Task(input);
                toDoList.addToList("X", task);
            }


        }

    }
}
