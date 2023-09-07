class Manager {
    List list;

    public Manager() {
        this.list = new List();
    }
    void process(String input) {
        String[] splitInput = input.split(" ", 2);
        String action = splitInput[0];

        if (action.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        } else if (action.equals("list")){
            System.out.println("Here are the tasks in your list: \n");
            System.out.println(this.list);
        } else if (action.equals("mark")){
            int taskId = Integer.parseInt(splitInput[1]);
            this.list.mark(taskId);

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.list.getTask(taskId));
        } else if (action.equals("unmark")) {
            int taskId = Integer.parseInt(splitInput[1]);
            this.list.unmark(taskId);

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.list.getTask(taskId));
        } else {
            this.list.add(input);
            int listSize = this.list.getSize();

            System.out.println("Got it. I've added this task:");
            System.out.println(this.list.getTask(listSize));
            System.out.println(String.format("Now you have %d tasks in the list.", listSize));
        }
    }
}
