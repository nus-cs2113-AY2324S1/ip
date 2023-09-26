package commands;
import ascii.AsciiArt;
import main.ResponseProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a general Command with basic functionalities
 */
public abstract class Command {
    public abstract void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException;

    /**
     * A method to check if the response given is valid
     *
     * @param response The input
     */
    public void isValidTask(String response){
        if (response.isBlank()){
            throw new IllegalArgumentException ("uwu master please put in a task description! " + AsciiArt.getArt("sad"));
        }
    }

    /**
     * A method to get the index of a certain string
     *
     * @param response The input
     * @param content The string to find
     * @return The starting index of 'content' in 'response'
     */
    public int getIndex(String response, String content){
        int index = response.indexOf(content);
        if(index == -1){
            throw new IllegalArgumentException ("You need " + content +" in you command uwu! " + AsciiArt.getArt("sad"));
        }
        return index;
    }
  
    public String processDate (String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
            return parsedDate.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return date;
        }
    }
  
    /**
     * A method to get the index of a certain string
     *
     * @param value The integer to check and convert
     * @param processor The main processor of the code
     * @return the value in integer form and accounted to start from 0
     */
    public int parseInt(String value, ResponseProcessor processor){
        try {
            int num = Integer.parseInt(value) - 1;
            if ((0 <= num) && (num < processor.taskList.size())){
                return num;
            }
            throw new IllegalArgumentException ("That is not a valid number masta! " + AsciiArt.getArt("depress"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("Please put in a number masta! " + AsciiArt.getArt("depress"));
        }
    }
}
