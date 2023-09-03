import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(" Hello! I'm Bot Hilary ");
        Task[] list = new Task[100];
        System.out.println(" What can I do for you? ");
        String line = in.nextLine();
        while (!line.equals("bye")){
            if (line.equals("list")) {
                for (int i = 0; i < Task.listCount; ++i) {
                    System.out.print(i + 1 + ". [");
                    System.out.println(list[i].getStatusIcon() + "]" + list[i].getDescription());
                }
            }
            else if (line.contains("unmark")){
                line = line.replace("unmark ", "");
                list[Integer.parseInt(line) - 1].unmark();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   [ ]" + list[Integer.parseInt(line) - 1].getDescription());
            }
            else if (line.contains("mark")){
                line = line.replace("mark ", "");
                list[Integer.parseInt(line) - 1].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [X]" + list[Integer.parseInt(line) - 1].getDescription());
            }
            else{
                list[Task.listCount] = new Task(line);
                Task.listCount++;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("  Bye. Hope to see you again soon! ");
    }
}
