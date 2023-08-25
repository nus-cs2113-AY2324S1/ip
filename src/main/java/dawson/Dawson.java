package dawson;

public class Dawson {
    /**
     * Print given input text together with a line as separator
     * 
     * @param text
     */
    public static void printText(String text) {
        System.out.println(text);
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {

        String dawsonText = 
            "██████╗░░█████╗░░██╗░░░░░░░██╗░██████╗░█████╗░███╗░░██╗\n" +
            "██╔══██╗██╔══██╗░██║░░██╗░░██║██╔════╝██╔══██╗████╗░██║\n" +
            "██║░░██║███████║░╚██╗████╗██╔╝╚█████╗░██║░░██║██╔██╗██║\n" +
            "██║░░██║██╔══██║░░████╔═████║░░╚═══██╗██║░░██║██║╚████║\n" +
            "██████╔╝██║░░██║░░╚██╔╝░╚██╔╝░██████╔╝╚█████╔╝██║░╚███║\n" +
            "╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═════╝░░╚════╝░╚═╝░░╚══╝";

        String welcomeText = " What can I do for you?";
        String exitString = " Bye. Hope to see you again soon!";

        printText("");
        printText("Hello! My name is \n" + dawsonText);
        printText(welcomeText);
        printText(exitString);
    }
}
