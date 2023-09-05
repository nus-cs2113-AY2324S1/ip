public class List {
    private String[] list;
    private int counter;

    public List() {
        this.list = new String[100];
        this.counter = 0;
    }

    public void print_list() {
        if (this.counter == 0) {
            System.out.println("No Entry");
            return;
        }
        for (int i=0; i<this.counter; i++) {
            System.out.println((i+1) + ". " + this.list[i]);
        }
    }

    public void add_to_list(String arg) {
        this.list[this.counter] = arg;
        this.counter++;
        System.out.println("added: " + arg);
    }
}
