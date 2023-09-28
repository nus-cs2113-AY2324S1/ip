package notGPT.parser;

public class Parser {
    public String[] parseCommand(String command) {
        return command.split(" ");
    }
}