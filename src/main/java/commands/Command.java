package commands;
import ascii.AsciiArt;
import main.ResponseProcessor;

public abstract class Command {
    public abstract void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException;

    public void isValidTask(String response){
        if (response.isBlank()){
            throw new IllegalArgumentException ("uwu master please put in a task description! " + AsciiArt.getArt("sad"));
        }
    }

    public int getIndex(String response, String content){
        int index = response.indexOf(content);
        if(index == -1){
            throw new IllegalArgumentException ("You need " + content +" in you command uwu! " + AsciiArt.getArt("sad"));
        }
        return index;
    }

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
