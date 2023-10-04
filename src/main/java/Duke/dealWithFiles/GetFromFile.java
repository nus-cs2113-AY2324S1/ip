package Duke.dealWithFiles;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import Duke.inputProcess.TaskList;
import java.util.Scanner;

public class GetFromFile {

    private String path;

    public GetFromFile(String path){
        this.path = path;
    }

    public void getFromTextFile(TaskList list) throws FileNotFoundException {
        File file = new File(path); // create a File for the given file path
        try {
            if(file.createNewFile()) {
                return;
            }
        } catch(IOException e){
            System.out.println("Something went wrong");
        }
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        int lineCount = 0;
        while (s.hasNext()) {
            String textLine = s.nextLine();
            try {
                String commandFromFile = textLine.split(" \\| ")[0];
                String isDoneFromFile = textLine.split(" \\| ")[1];
                String taskFromFile = textLine.split(" \\| ")[2];
                String timeFromFile = "";
                if (!commandFromFile.equals("T")) {
                    timeFromFile = textLine.split(" \\| ")[3];
                }
                switch (commandFromFile){
                case "T":
                    list.addTodo(taskFromFile);
                    break;
                case "D":
                    list.addDeadline(taskFromFile, LocalDateTime.parse(timeFromFile));
                    break;
                case "E":
                    LocalDateTime start = LocalDateTime.parse(timeFromFile.split(" to ", 2)[0]);
                    LocalDateTime end = LocalDateTime.parse(timeFromFile.split(" to ", 2)[1]);
                    list.addEvent(taskFromFile, start, end);
                    break;
                default:
                }
                if(isDoneFromFile.equals("1")) {
                    list.get(lineCount).markAsDone();
                }
                lineCount++;
            } catch(IndexOutOfBoundsException e){
                System.out.println("The file not in the correct format");
            }
        }
    }
}
