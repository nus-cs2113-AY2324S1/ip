import java.util.Scanner;

public class Zran {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________";

    public static void main(String[] args) {
        System.out.printf((outputFormat) + "%n",
                "hello! I'm Zran, your personal assistant:)! \n" +
                "    Type in your to dos for the day and press enter! \n " +
                "   Type 'list' to view your to do list. \n " +
                "   Type 'bye' to end my service.");
        addItems();
    }

    //The function 'echo' is used to print any output to the user
    public static void echo(String input, String[] items) {
        if(!input.equals("list")) {
            System.out.printf((outputFormat) + "%n", "New task added: " + input);
        } else {
            System.out.println("    ____________________________________________________________");
            int index=0;
            for(String item : items){
                if((item != null)) {
                    System.out.println("    "+ ++index + ". " + item);
                }
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    //The function 'addItems' is used to accept user's input and add it to their To Do list
    public static void addItems() {
        Scanner in = new Scanner(System.in);
        String input = "";

        String[] items = new String[100];
        int index = 0;

        while(!(input = in.nextLine()).equals("bye")){
            if(!input.equals("list")){
                items[index++] = input;
            }
            echo(input, items);
        }

        System.out.printf((outputFormat) + "%n", "have a great day ahead!");
    }
}
