package ZenBot;
import java.util.Scanner;

public class Input {
    private Scanner input = new Scanner(System.in);
    private String line = "";
    private String[] inputTokens = new String[0];

    public String getInput() {
        line = input.nextLine().trim();
        inputTokens = line.split(" ");
        return line;
    }

    public String getLine() {
        return line;
    }
    
    public String[] getInputTokens() {
        return inputTokens;
    }

    public String getCommand() {
        return inputTokens[0];
    }
}
