import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        String greetingMsg = "____________________________________________________________\n" +
                " Hello! I'm Chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greetingMsg);


        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
            input = in.nextLine();
        }
        System.out.println(byeMsg);

    }
}
