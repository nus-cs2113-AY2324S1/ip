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
        for (String item : items) {
            output = item + "\n";
        }
        return output;
    }
}
