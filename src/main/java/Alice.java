import java.util.Scanner;

public class Alice {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String helloMessage = " Hi there, I'm Alice, a chatbot!\n What can I do for you?\n";
        Scanner in = new Scanner(System.in);
        System.out.println(helloMessage + line);
        String userInput = in.nextLine();
        while (!(userInput.equals("bye"))){
            System.out.println(" " + userInput + "\n" + line);
            userInput = in.nextLine();
        }
        String byeMessage = " Bye. Hope to see you again soon!\n";
        System.out.println(byeMessage + line);
    }
}
