package duke;

import java.io.IOException;
import java.util.Arrays;


public class Parser extends DukeException{

    private final TaskList taskList;
    private final FileManager writer;

    public Parser(String[] userInput, TaskList taskList, FileManager writer) {
        super(userInput);
        this.taskList = taskList;
        this.writer = writer;
    }

    public void parseInput() throws IOException {
        super.checkInput(taskList.getSize());
        if (!super.exception){
            switch (userInput[0]) {
            case "mark":
                taskList.markItem(Integer.parseInt(userInput[1]) - 1, true);
                break;
            case "unmark":
                taskList.markItem(Integer.parseInt(userInput[1]) - 1, false);
                break;
            case "bye":
                writer.save(TaskList.taskList);
                writer.closeFile();
                new Ui().printBye();
                System.exit(0);
                break;
            case "list":
                taskList.listTask();
                break;
            case "delete":
                taskList.deleteTask(Integer.parseInt(userInput[1]) - 1);
                break;
            case "find":
                taskList.findTask(String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length)));
            default:
                taskList.addTasks(userInput);
                break;
            }
        }
    }
}
