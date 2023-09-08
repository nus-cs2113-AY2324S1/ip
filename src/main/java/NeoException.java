public class NeoException extends Exception {
    protected String description;
    protected boolean isEmpty;

    public NeoException(String description, boolean isEmpty) {
        this.description = description;
        this.isEmpty = isEmpty;
    }

    public void printException() {
        if (isEmpty && description.equals("description")) {
            System.out.println("OOPS!!! The description cannot be left empty.");
        } else if (isEmpty) {
            System.out.println("OOPS!!! The description of " + description + " cannot be left empty.");
        } else {
            System.out.println("OOPS!!! Please check that " + description + " is included in the command.");
        }
    }
}