import java.util.ArrayList;

public class List {
    ArrayList<String> items;

    public List() {
        this.items = new ArrayList<String>(100);
    }

    public void add(String item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= items.size(); i++) {
            output = output + i + ". " + this.items.get(i-1) + "\n";
        }
        return output;
    }
}
