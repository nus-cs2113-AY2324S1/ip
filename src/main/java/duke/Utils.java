package duke;

public class Utils {
    public static void echo(String input) {
        System.out.println(input + "\n");
        printDivider();

    }
    public static void  printDivider(){
        System.out.println("___________________________________\n");
    }

    public static void handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException e){
        Utils.printDivider();
        System.out.println("OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
        Utils.printDivider();
    }

    public static void handleNoTaskNameException(){
        Utils.printDivider();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        Utils.printDivider();
    }

    public static void handleArrayIndexOutOfBoundsException(){
        Utils.printDivider();
        System.out.println("OOPS!!! Please Input in the correct format!");
        Utils.printDivider();
    }
}
