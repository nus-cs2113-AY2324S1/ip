class Manager {
    void process(String input) {
        String convertedInput = input.toLowerCase();
        if (convertedInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        } else {
            System.out.println(input);
        }
    }
}
