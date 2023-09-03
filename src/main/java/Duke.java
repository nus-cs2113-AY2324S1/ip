public class Duke {
    public static void main(String[] args) {
        printLines(30,'-');
        System.out.println("Hello! I'm En\nWhat can i do for you?");
        printLines(30,'-');
        System.out.println("Bye! Have a nice day");
        printLines(30,'-');
   }
    public static void printLines(int l, char c){
        for (int i = 0; i < l; i++){
            System.out.print(c);
        }
        System.out.println();
    }
}
