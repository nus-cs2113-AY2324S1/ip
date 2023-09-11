public class Utils {
    public static void echo(String input) {
        System.out.println(input + "\n");
        printDivider();

    }
    public static void  printDivider(){
        System.out.println("___________________________________\n");
    }

    public static void handleStringIndexOutOfBoundsException(){
        Utils.printDivider();
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        Utils.printDivider();
    }

    public static void handleNoTaskNameException(){
        Utils.printDivider();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        Utils.printDivider();
    }
}
