class Manager {
    List list;

    public Manager() {
        this.list = new List();
    }
    void process(String input) {
        String convertedInput = input.toLowerCase();
        if (convertedInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        } else if (convertedInput.equals("list")){
            System.out.println(this.list);
        } else {
            this.list.add(input);
        }
    }
}
