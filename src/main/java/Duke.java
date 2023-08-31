import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(" Hello! I'm Bot Hilary ");
        System.out.println("  What can I do for you? ");
        String line = in.nextLine();
        if (line.equals("bye")) {
            System.out.println("  Bye. Hope to see you again soon! ");
        }
        else{
            System.out.println(line);
        }
    }
}
