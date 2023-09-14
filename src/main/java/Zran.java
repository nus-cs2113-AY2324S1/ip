import java.util.Scanner;

public class Zran {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________";

    public static void main(String[] args) throws ZranExceptions {
        System.out.printf((outputFormat) + "%n",
                "hello! I'm Zran, your personal assistant:)! \n" +
                "    Type in your to dos for the day and press enter! \n " +
                "   Type 'help' to view to list of commands the bot accepts! ");
        TaskItems.addItems();
    }

}
