import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(" Hello! I'm Bot Hilary ");
        String[] list = new String[100];
        boolean[] isDone = new boolean[100];
        int listCount = 0;
        System.out.println(" What can I do for you? ");
        String line = in.nextLine();
        while (!line.equals("bye")){
            if (line.equals("list")) {
                for (int i = 0; i < listCount; ++i) {
                    System.out.print(i + 1 + ". [");
                    System.out.println(((isDone[i]) ? "X" : " " )+ "]" + list[i]);
                }
            }
            else if (line.contains("unmark")){
                line = line.replace("unmark ", "");
                isDone[Integer.parseInt(line) - 1] = false;
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   [ ]" + list[Integer.parseInt(line) - 1]);
            }
            else if (line.contains("mark")){
                line = line.replace("mark ", "");
                isDone[Integer.parseInt(line) - 1] = true;
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [X]" + list[Integer.parseInt(line) - 1]);
            }
            else {
                list[listCount] = line;
                listCount++;
                System.out.println("Added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("  Bye. Hope to see you again soon! ");
    }
}
