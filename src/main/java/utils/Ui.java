package utils;

import task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    public Ui() {
        echo("Hello! I'm Mark\nWhat can I do for you?");
    }

    public String wrapMessage(String message) {
        return "____________________________________________________________\n" +
                message +
                "\n____________________________________________________________";
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void echo(String message) {
        System.out.println(wrapMessage(message));
    }

    public void printList(List<Task> tasks) {
        Task[] listAsArray = tasks.toArray(new Task[0]);
        StringBuilder out = new StringBuilder();
        if (listAsArray.length == 0) {
            out = new StringBuilder("List is empty.");
        }
        for (int i = 0; i < listAsArray.length; i++) {
            out.append(i == 0 ? "" : "\n").append(i + 1).append(". ").append(listAsArray[i].getListText());
        }
        echo(out.toString());
    }

}
