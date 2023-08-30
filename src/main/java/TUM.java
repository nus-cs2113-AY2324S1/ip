import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TUM {
    public static void main(String[] args) {
        List<String> textList = new LinkedList<>();
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUM");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                for(int i = 0; i < textList.size(); i++) {
                    System.out.println((i+1) + ". " + textList.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                textList.add(line);
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
