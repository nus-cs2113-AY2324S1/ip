import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Greetings
        String intro = "Hello! I'm Wenny!\n"
                        + "How may I help you?\n";
        System.out.println(intro);
        Task tasks = new Task();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                bye();
            } else if (userInput.equals("list")) {
                tasks.print_tasks();
            } else {
                String[] substr = userInput.split("\\s+");
                if (substr[0].equals("mark")) {
                    tasks.markAsDone(Integer.parseInt(substr[1]));
                } else if (substr[0].equals("unmark")) {
                    tasks.markAsUndone(Integer.parseInt(substr[1]));
                } else {
                    tasks.add_to_tasks(userInput);
                }
            }
        }
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
