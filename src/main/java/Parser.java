import java.util.ArrayList;
public class Parser {
    /**
     * Parse the input command.
     *
     * @param input The input command.
     * @return The parsed input.
     */
    public ArrayList<String> parse(String input) throws DukeException {
        ArrayList<String> result = new ArrayList<String>();
        String[] temp = input.split(" ");
        result.add(temp[0]);
        if(temp[0].equals("todo")) {
            try {
                result.add(input.substring(5));
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if(temp[0].equals("deadline")) {
            try {
                result.add(input.split(" /")[0].substring(9));
                result.add(input.split(" /")[1]);
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a deadline should use / mark time.");
            }
        } else if(temp[0].equals("event")) {
            try {
                result.add(input.split(" /")[0].substring(6));
                result.add(input.split(" /")[1]);
                result.add(input.split(" /")[2]);
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a event should use / mark start time and end time.");
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
        } else if(temp[0].equals("mark")||temp[0].equals("unmark")||temp[0].equals("delete")) {
            try {
                result.add(temp[1]);
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The index of instruction " + temp[0] + " cannot be empty.");
            }
        } else if(temp[0].equals("find")) {
            try {
                result.add(input.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
            }
        } else if(temp[0].equals("bye")||temp[0].equals("list")) {
            if(temp.length!=1){
                throw new DukeException("☹ OOPS!!! The instruction " + temp[0] + " cannot have other words.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return result;
    }
}
