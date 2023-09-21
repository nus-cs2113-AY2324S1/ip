package chattie;

import chattie.tasks.TaskFile;

import java.io.IOException;

public class Chattie {
    public static void main(String[] args) {
        try {
            TaskFile.loadFile();
        } catch (IOException e) {
            System.out.println("\tUnable to create chattie.txt");
        }
        Chat.startChat();
    }
}
