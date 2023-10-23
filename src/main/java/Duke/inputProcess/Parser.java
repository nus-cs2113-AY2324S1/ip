package duke.inputProcess;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Parser {
    private String taskToAdd;
    private String userInput;
    protected LocalDateTime timeGetFromText1;
    protected LocalDateTime timeGetFromText2;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    
    public Parser(String userInput){
        this.userInput = userInput;
    }

    public Parser getDeadlineInput(){
        timeGetFromText1 = LocalDateTime.parse(userInput.split("/by ", 2)[1], df);
        taskToAdd = userInput.split("/", 2)[0];
        return this;
    }

    public Parser getEventInput(){
        taskToAdd = userInput.split("/",2)[0];
        String eventTime = userInput.split("/from ", 2)[1];
        timeGetFromText1 = LocalDateTime.parse(eventTime.split(" /to ", 2)[0], df);
        timeGetFromText2 = LocalDateTime.parse(eventTime.split(" /to ", 2)[1], df);
        return this;
    }

    public String getCommand(){
        return userInput.split(" ", 2)[0].toLowerCase();
    }
    public String getRemainingPart(){
        return userInput.split(" ", 2)[1];
    }

    public void newUserInput(String userInput){
        this.userInput = userInput;
    }

    public String getTaskName() {
        return taskToAdd;
    }

    public LocalDateTime getTaskTime1() {
        return timeGetFromText1;
    }

    public LocalDateTime getTaskTime2() {
        return timeGetFromText2;
    }
}

