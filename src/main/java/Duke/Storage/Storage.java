package Duke.Storage;

import Duke.Command.Command;
import Duke.Exception.NoDateTimeSpecifiedException;
import Duke.Exception.NoTaskSpecifiedException;
import Duke.Task.Task;
import Duke.Task.TaskList;

import java.io.File;
import java.util.Scanner;

public class Storage {

    public Storage(){

    }

    public boolean verifyStorageFilePresent(File directory, File file) {
        boolean isDirectoryPresent = directory.exists() && directory.isDirectory();
        if (file.exists()) {
            return true;
        } else if (!isDirectoryPresent) {
            try {
                directory.mkdir();
            } catch (Exception e) {
                System.out.println("Unable to create folder " + directory);
            }
        }
        return false;
    }

    public void loadTaskList(Scanner s, TaskList records) {
        while (s.hasNext()) {
            String storedMessage = s.nextLine();
            String[] messageFragments = storedMessage.split("\\|");
            Task task = null;
            switch (messageFragments[0].trim()) {
            case ("T"):
                try {
                    task = Command.createToDo(messageFragments[2]);
                } catch (NoTaskSpecifiedException e) {
                    System.out.println("Failed loading Todo. Todo will be deleted.");
                }
                break;
            case ("D"):
                try {
                    task = Command.createDeadline(messageFragments[2]);
                } catch (NoTaskSpecifiedException | NoDateTimeSpecifiedException e) {
                    System.out.println("Failed loading Deadline. Deadline will be deleted.");
                }
                break;
            case ("E"):
                try {
                    task = Command.createEvent(messageFragments[2]);
                } catch (NoTaskSpecifiedException | NoDateTimeSpecifiedException e) {
                    System.out.println("Failed loading Event. Event will be deleted.");
                }
                break;
            default:
                continue;
            }
            if (task != null) {
                Command.addTaskToList(task, records);
            }
            if (messageFragments[1].trim().equals("1")) {
                //mark the previous task as done.
                records.getTask(records.getNumTask() - 1).setDone();
            }
        }
    }


    //will need a separate encoder and decoder for the tasks to be saved in storage files.
}
