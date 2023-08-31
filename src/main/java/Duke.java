import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello my name is DUKE");
        System.out.println("What can I do for you today?");

        boolean echo = true;
        while (echo){
            String input;
            Scanner scan = new Scanner( System.in );
            input = scan.nextLine();
            if (!input.equals("bye")){
               System.out.println(input);
            }
            else{
                echo = false;
            }


        }
        System.out.println("Fine. If you have no ideas Imma head out");

    }
}
