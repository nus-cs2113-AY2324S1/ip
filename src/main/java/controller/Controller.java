package controller;

import commands.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {
    private final Storage storage;

    private final TaskList tasks;

    private static Ui ui;

    public Controller(TaskList tasks, Storage storage){
        this.tasks = tasks;
        this.storage = storage;
    }

    public void event(String userInput) {
        String newWord = userInput.replace("event ", "");
        String[] firstSplit = newWord.split("/");
        if (!Parser.validateTimedTasks("event", firstSplit)) {
            return;
        }
        Event currTask = new Event(firstSplit[0], firstSplit[1], firstSplit[2]);
        tasks.add(currTask);

        storage.addToFile("E|0|" + firstSplit[0] + "|" + firstSplit[1] + "|" + firstSplit[2]);

        System.out.println("added: " + firstSplit[0] + ", you have now " + tasks.size() + " tasks");
    }

    public void deadline(String userInput) {
        String newWord = userInput.replace("deadline ", "");
        String[] deadlineArray = newWord.split("/");
        if (!Parser.validateTimedTasks("deadline", deadlineArray)) {
            return;
        }
        Deadline currTask = new Deadline(deadlineArray[0], deadlineArray[1]);
        tasks.add(currTask);
        System.out.println("added: " + deadlineArray[0]);
        storage.addToFile("D|0|" + deadlineArray[0] + "|" + deadlineArray[1]);
    }

    public void todo(String userInput) {
        String newWord = userInput.replace("todo ", "");
        ToDo currTask = new ToDo(newWord);
        tasks.add(currTask);
        System.out.println("added: " + newWord);
        storage.addToFile("T|0|" + newWord);
    }

    public void unmark(String[] words) {
        System.out.println("unmark " + words[1]);
        int index = Integer.parseInt(words[1]);
        // we need to check if it's out of bounds first
        Task currTask = tasks.get(index - 1);
        currTask.unmarkStatus();
        System.out.println("Nice! I've unmarked this task: ");
        String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
        System.out.println(message);
        storage.updateMark(index, 0);
    }

    public void mark(String[] words) {
        int index = Integer.parseInt(words[1]);
        Task currTask = tasks.get(index - 1);
        currTask.markStatus();
        System.out.println("Nice! I've marked this task as done: ");
        String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
        System.out.println(message);
        storage.updateMark(index, 1);
    }

    public void find(String[] words) {
        String keyword = words[1];
        AtomicBoolean matchFound = new AtomicBoolean(false);
        System.out.println("Here are the tasks that contains the keyword '" + keyword + "'");
        tasks.getAllTasks().stream()
                .filter(task -> {
                    if (!task.getDescription().contains(keyword)) {
                        return false;
                    }
                    matchFound.set(true);
                    return true;
                }).forEach(System.out::println);
        if (!matchFound.get()) {
            System.out.println("No matching items found");
        }
    }

    public void delete(String[] words) {
        int index = Integer.parseInt(words[1]);
        Task item = tasks.get(index - 1);
        storage.removeFromFile(index);
        tasks.delete(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("[" + item.getType() + "]" + "[" + item.getStatusIcon() + "]" + item);
        return;
    }
}
