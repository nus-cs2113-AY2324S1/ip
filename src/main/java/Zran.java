import java.util.Scanner;

public class Zran {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________";

    public static void main(String[] args) throws ZranExceptions {
        System.out.printf((outputFormat) + "%n",
                "hello! I'm Zran, your personal assistant:)! \n" +
                "    Type in your to dos for the day and press enter! \n " +
                "   Type 'list' to view your ,to do list. \n " +
                "   Type 'bye' to end my service. \n" +
                "    Type 'mark' followed by item number to mark item as done. \n" +
                "    Type 'unmark' followed by item number to unmark the item." );
        TaskItems.addItems();
    }

}
