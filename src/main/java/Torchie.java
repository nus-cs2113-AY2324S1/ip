import java.util.Scanner;
public class Torchie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Torchie!");
        System.out.println("What can I do for you?");
        System.out.println("Let's play copytorchie today! You say something and I ll copy!");

        String input;

        do {
            input = scanner.nextLine();
            if (!input.equals("bye")){
                System.out.println(input);
            }
        } while (!input.equals("bye"));

        System.out.println("Awww bye :( Let's play again soon!");
    }
}
