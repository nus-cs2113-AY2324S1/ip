package duke.parser;

public class TaskParser {

    public int parseIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    public String[] parseTask(String input, String regex) {
        return input.split(regex);
    }
}
