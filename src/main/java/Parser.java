import java.util.regex.*;

public class Parser {
    public String parseCommandInput(String line) {
        String[] words = line.split(" ", 2);
        return words[0];
    }


}

