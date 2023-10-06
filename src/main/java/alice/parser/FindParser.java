package alice.parser;

import alice.exceptions.InvalidCommandException;
import alice.exceptions.InvalidFormatException;

public class FindParser {
    String userInput;
    public FindParser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Process user input to get keyword that user wants to find
     *
     * @return keyword the word user is searching for in tasks
     * @throws InvalidFormatException the format of search input is wrong
     */
    public String getKeyword() throws InvalidFormatException {
        String[] userInputArray = userInput.split(" ");
        int arrayLength = userInputArray.length;

        if (arrayLength != 2) {
            throw new InvalidFormatException();
        }

        return userInputArray[1];
    }
}
