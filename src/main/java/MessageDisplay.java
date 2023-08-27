import java.util.Scanner;  // Import the Scanner class
public class MessageDisplay {
    static Scanner input = new Scanner(System.in);  // Create a Scanner object
    static String logo = "     ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";
    public static void displayOpeningMessage(){
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    今日は! I am Rene Kokoro!");
        System.out.println("    How may I assist you today? *blushes*");
        System.out.println("    ____________________________________________________________");
    }

//    public static void echo(){
//        String userInput = "";
//        while(!userInput.equals("bye")){
//            userInput = input.nextLine();r
//            System.out.println("    ____________________________________________________________");
//            System.out.println("OuO you said " + userInput + "!");
//            System.out.println("    ____________________________________________________________");
//        }
//        displayCLosingMessage();
//    }

    public static void addInput(){
        String userInput = "";
        while(!userInput.equals("bye")){
            userInput = input.nextLine();
            System.out.println("    ____________________________________________________________");
            if(userInput.equals("bye")){
                break;
            }
            else if(userInput.equals("list")){
                InputList.printInputList();
            } else {
                InputList.addToInputList(userInput);
                System.out.println("I have added: " + userInput + "! OwO");
            }
            System.out.println("    ____________________________________________________________");
        }
        displayCLosingMessage();
    }

    public static void displayCLosingMessage(){
        System.out.println("    Aww you are leaving? *sniffs*");
        System.out.println("    Well... hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
