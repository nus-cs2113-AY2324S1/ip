package utils;

import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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

    public void printListByDate(List<Task> tasks, String date) {
        if (isValidDate(date)) {
            StringBuilder out = new StringBuilder("These are the tasks due on " + date + ":\n");
            AtomicInteger i = new AtomicInteger();
            tasks.stream()
                    .filter(task -> {
                        if (task instanceof Deadline) {
                            return ((Deadline) task).getBy().equals(date);
                        } else if (task instanceof Event) {
                            return ((Event) task).getTo().equals(date);
                        }
                        return false;
                    }).forEach(task -> out.append(i.get() == 0 ? "" : "\n").append((i.getAndIncrement()) + 1).append(". ").append(task.getListText()));
            echo(i.get() == 0 ? "Nothing due today!" : out.toString());
        } else {
            echo("☹ OOPS!!! I'm sorry, the date should be in the format of yyyy-MM-dd.");
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
