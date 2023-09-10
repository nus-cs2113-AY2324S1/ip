import java.util.Scanner;

public class Chattie {

    private static void startChat(int count, Task[] list) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Chattie! How was your day?");
        System.out.println("\t____________________________________________________________");

        line = in.nextLine();
        greetUser(line);

        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            count = readCommands(line, list, count);
            System.out.println("\t____________________________________________________________");
            line = in.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tByeeeee. Hope to see you again soon! :)");
        System.out.println("\t____________________________________________________________");
    }

    public static void greetUser(String line) {
        line = line.toLowerCase();

        System.out.println("\t____________________________________________________________");
        if(line.contains("good")) {
            System.out.println("\tGreat to hear that! :D");
        } else if (line.contains("bad")) {
            System.out.println("\tI'm sorry to hear that :(");
        } else {
            System.out.println("\tSounds like you had quite a day");
        }
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    private static int readCommands(String line, Task[] list, int count) {
        if(line.equals("list")) {
            listTasks(list, count);
        } else if(line.startsWith("mark") || line.startsWith("unmark")) {
            String[] command = line.split(" ");
            markTask(list, command);
        } else if (line.startsWith("todo")) {
            count = addTodo(list, line, count);
        } else if (line.startsWith("deadline")) {
            count = addDeadline(list, line, count);
        } else if (line.startsWith("event")) {
            count = addEvent(list, line, count);
        }
        return count;
    }

    public static void listTasks(Task[] list, int count) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print("\t" + (i+1) + ". ");
            if(list[i].isDone()) {
                System.out.println(list[i]);
            } else {
                System.out.println(list[i]);
            }
        }
    }

    public static int addTodo(Task[] list, String line, int count) {
        list[count] = new Todo(line.substring(5));
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + list[count]);
        count++;
        System.out.println("\tNow you have " + count + " tasks in the list.");
        return count;
    }

    public static int addDeadline(Task[] list, String line, int count) {
        int slashIndex = line.indexOf("/by");
        String task = line.substring(9, slashIndex);
        String by = line.substring(slashIndex + 3);
        list[count] = new Deadline(task, by);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + list[count]);
        count++;
        System.out.println("\tNow you have " + count + " tasks in the list.");
        return count;
    }

    public static int addEvent(Task[] list, String line, int count) {
        int firstSlash = line.indexOf("/from");
        int secondSlash = line.indexOf("/to");
        String task = line.substring(6, firstSlash);
        String from = line.substring(firstSlash + 5, secondSlash);
        String to = line.substring(secondSlash + 3);
        list[count] = new Event(task, from, to);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + list[count]);
        count++;
        System.out.println("\tNow you have " + count + " tasks in the list.");
        return count;
    }

    public static void markTask(Task[] list, String[] command) {
        int taskNum = Integer.parseInt(command[1]) - 1;
        if(command[0].equalsIgnoreCase("mark")) {
            list[taskNum].setDone(true);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + list[taskNum]);
        } else {
            list[taskNum].setDone(false);
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t" + list[taskNum]);
        }
    }
    
    public static void main(String[] args) {
        int count = 0;
        Task[] list = new Task[100];
        startChat(count, list);
    }
}
