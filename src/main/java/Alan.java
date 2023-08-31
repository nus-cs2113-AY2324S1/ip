public class Alan {
    public static void printGreet() {
        printHorizontalLine();
        String man = " @/\n" +
                     "/| \n" +
                     "/ \\";

        System.out.println(man);
        System.out.println("Hello! I'm Alan");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        printGreet();
        printExit();
    }
}
