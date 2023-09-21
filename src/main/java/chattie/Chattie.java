package chattie;

import chattie.tasks.Task;
import chattie.tasks.TaskFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Chattie {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        try {
            TaskFile.loadFile();
            list.addAll(TaskFile.readFromFile());
            System.out.println(list);
        } catch (FileNotFoundException e) {
            System.out.println("\tUnable to find chattie.txt");
        } catch (IOException e) {
            System.out.println("\tUnable to create chattie.txt");
        }
        int count;
        if (list.size() < 1) {
            count = 0;
        } else {
            count = list.size();
        }
        Chat.startChat(list, count);
    }
}
