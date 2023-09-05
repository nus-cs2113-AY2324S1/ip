import java.util.Scanner;

public class itay {

    static Task[] tasks = new Task[100];
    static int numTasks = 0;

    public static void Respond(String line) {
        String parts[] = line.split(" ");

        if(line.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < numTasks ; i++) {
                System.out.println(i + 1 + ".[" + tasks[i].getStatus() + "] " + tasks[i].description);
            }

        } else if(parts[0].equals("mark")) {
            int idx = Integer.parseInt(parts[1]) - 1;
            if(idx < 0 || idx >= numTasks) {
                System.out.println("Please enter a valid task number");
                return;
            }
            tasks[idx].isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + tasks[idx].getStatus() + "] " + tasks[idx].description);

        } else if(parts[0].equals("unmark")) {
            int idx = Integer.parseInt(parts[1]) - 1;
            if(idx < 0 || idx >= numTasks) {
                System.out.println("Please enter a valid task number");
                return;
            }
            tasks[idx].isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + tasks[idx].getStatus() + "] " + tasks[idx].description);

        } else {
            addTask(line);
        }
    }
    
    public static void addTask(String line) {
        Task task = new Task(line);
        tasks[numTasks] = task;
        numTasks++;
        System.out.println("added: " + line);   
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // greet
        System.out.println("Hello! I'm Itay");
        System.out.println("What can i do for you?");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        line = line.trim();

        while(! line.equals("bye")) {
            Respond(line);
            line = in.nextLine();
            line = line.trim();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }


    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            isDone = false;
        }

        public String getStatus() {
            return (this.isDone ? "X" : " ");
        }
    }   
}