public class Alan {
    public static void printGreet() {
        String man = " @/\n" +
                     "/| \n" +
                     "/ \\";

        System.out.println(man);
        System.out.println("Hello! I'm Alan");
        System.out.println("What can I do for you?");
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printHorizontalLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        printHorizontalLine();
        printGreet();
        printHorizontalLine();
        printExit();
        printHorizontalLine();
    }
}
