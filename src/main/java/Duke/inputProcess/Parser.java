package Duke.inputProcess;
import java.time.Instant;
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
    /*
    protected TaskList tasks;
    protected Scanner scanner;
    protected SaveToFile save;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Parser(TaskList tasks, SaveToFile save){
        this.tasks = tasks;
        this.scanner = new Scanner(System.in);
        this.save = save;
    }

    public void start() {
        String userInput;
        do {
            userInput = scanner.nextLine();
            handleUserInput(userInput);
            try {                                           // Save tasks to file after every input
                save.saveToTextFile(tasks);                 // To avoid losing all tasks when crashed
            } catch(IOException e){
                System.out.println("Something wrong to save the file");
            }
        } while (!userInput.equals("bye"));
    }
    private void handleUserInput(String userInput) {
        String eventTime;
        String command = "list";
        if (!userInput.equals("list")){
            try{
                command = userInput.split(" ", 2)[0].toLowerCase();
                userInput = userInput.split(" ", 2)[1];
            } catch(IndexOutOfBoundsException e){
                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                return;
            }
        }
        switch (command){
        case "list":
            tasks.printList();
            System.out.println("\tNow you have " + tasks.size() + " in the list");
            break;
        case "unmark":
            tasks.unmark((Integer.parseInt(userInput) - 1));
            break;
        case "mark":
            tasks.mark((Integer.parseInt(userInput) - 1));
            break;
        case "deadline":
            try {
                try {
                    LocalDateTime taskDeadline = LocalDateTime.parse(userInput.split("/by ", 2)[1], df);
                    userInput = userInput.split("/", 2)[0];
                    tasks.addDeadline(userInput, taskDeadline);
                    System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.get(tasks.size() - 1) + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                } catch (DateTimeParseException e){
                    System.out.println("\tThe input format for deadline event need to be \"deadline deadlineEvent /by dd/MM/yyyy HHmm\"");
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("\tOOPS!!! The deadline need to separated by \"/by\"");
            }
            break;
        case "event":
            LocalDateTime start;
            LocalDateTime end;
            try {
                try {
                    eventTime = userInput.split("/from ", 2)[1];
                    start = LocalDateTime.parse(eventTime.split(" /to ", 2)[0], df);
                    end = LocalDateTime.parse(eventTime.split(" /to ", 2)[1], df);
                } catch (DateTimeParseException e) {
                    System.out.println("\tThe input format for event need to be \"event eventName /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\"");
                    break;
                }
                if (end.isBefore(start)){
                    System.out.println("\tStart time cannot be later than end time");
                    break;
                }
                userInput = userInput.split("/",2)[0];
                tasks.addEvent(userInput, start, end);
                System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.get(tasks.size() - 1) + "\n\tNow you have "+ tasks.size() + " tasks in the list.");
            } catch(IndexOutOfBoundsException e){
                System.out.println("\tOOPS!!! The event timing need to separated by \"/from\" and \"/to\"" );
            }
            break;
        case "todo":
            tasks.addTodo(userInput);
            System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.get(tasks.size() - 1) + "\n\tNow you have "+ tasks.size() + " tasks in the list.");
            break;
        case "delete":
            try {
                tasks.get(Integer.parseInt(userInput) - 1);
                tasks.deleteTask(Integer.parseInt(userInput) - 1);
            } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                System.out.println("OOPS!!! Need to specify which task want to delete");
            }
            break;
        case "find":
            tasks.find(userInput);
            break;
        default:
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
     */
}

