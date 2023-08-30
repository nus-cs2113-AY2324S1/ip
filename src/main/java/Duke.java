import java.util.Scanner;
public class Duke {
    public static final String line = "__________________________________________";
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I'm Chat0PT");
        System.out.println("What can I do for you?");
        System.out.println(line);
        String input;
        while(true){
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if (input.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            else{
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }


    }

}
