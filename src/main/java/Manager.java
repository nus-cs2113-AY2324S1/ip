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
            System.out.println(this.list);
        } else if (action.equals("mark")){
            this.list.mark(Integer.parseInt(splitInput[1]));
        } else if (action.equals("unmark")) {
            this.list.unmark(Integer.parseInt(splitInput[1]));
        } else {
            this.list.add(input);
        }
    }
}
