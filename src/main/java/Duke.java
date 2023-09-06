import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello my name is DUKE");
        System.out.println("What can I do for you today?");

        ArrayList<String> commandList = new ArrayList<String>(100);

        Scanner scan = new Scanner( System.in );

        boolean isEchoing = true;
        while (isEchoing){
            String input;
            input = scan.nextLine();

            if (input.equals("bye")){
                isEchoing = false;
                break;
            }

            if (!input.equals("list")){
                commandList.add(input);
                System.out.println("Added: " + input);
            } else if (input.equals("list")){
                for (int i = 0; i < commandList.size(); i++){
                    String listItem = commandList.get(i);
                    System.out.println(i + ": " + listItem);
                }
            }






        }
        System.out.println("Fine. If you have no ideas Imma head out");

    }
}
