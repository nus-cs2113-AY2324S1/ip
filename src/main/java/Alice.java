import java.util.Scanner;

public class Alice {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String helloMessage = " Hi there, I'm Alice, a chatbot!\n What can I do for you?\n";
        String[] allUserInput = new String[100];
        int inputCount = 0;
        Scanner in = new Scanner(System.in);
        System.out.println(helloMessage + line);
        String userInput = in.nextLine();
        while (!(userInput.equals("bye"))){
            if (userInput.equals("list")){
                for (int i=0; i<inputCount; i++){
                    System.out.printf("%d. %s\n", i+1, allUserInput[i]);
                }
                System.out.println(line);
            } else{
                allUserInput[inputCount] = userInput;
                inputCount++;
                System.out.println(" added: " + userInput + "\n" + line);
            }
            userInput = in.nextLine();
        }

        String byeMessage = " Bye. Hope to see you again soon!\n";
        System.out.println(byeMessage + line);
    }
}
