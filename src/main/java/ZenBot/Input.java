package ZenBot;
import java.util.Scanner;

public class Input {
    private Scanner input = new Scanner(System.in);
    private String line = "";

    public String getInput() {
        line = input.nextLine().trim();
        return line;
    }

}
