package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.helper.FormatCheck;
import chat0pt.helper.FormatString;
import chat0pt.helper.Printer;

public class CommandHandler {

    public static Task addTodo(String[] tokens){
        try {
            if (tokens.length > 1) {
                String[] todo = FormatString.format("todo",tokens);
                if (todo != null) {
                    return new Todo(todo[0]);
                } else{
                    throw new DukeException("Failed to add todo");
                }
            }else{
                Printer.invalidMessage("todo");
            }
        }
        catch (IndexOutOfBoundsException | NullPointerException ex){
            Printer.invalidMessage("todo");
        } catch (DukeException e) {
            String[] toPrint = {e.toString()};
            Printer.print(toPrint);
        }
        return null;
    }
    public static Task addDeadline(String[] tokens){
        try {
            if(FormatCheck.deadlineFormat(tokens)){
                String[] deadline = FormatString.format("deadline",tokens);
                if (deadline != null) {
                    return new Deadline(deadline[0], deadline[1]);
                } else{
                    throw new DukeException("Failed to add deadline");
                }
            }
            else{
                Printer.invalidMessage("deadline");
            }
        }
        catch (IndexOutOfBoundsException ioe){
            Printer.invalidMessage("deadline");
        } catch (DukeException e) {
            String[] toPrint = {e.toString()};
            Printer.print(toPrint);
        }
        return null;
    }

    public static Task addEvent(String[] tokens){
        try {
            if(FormatCheck.eventFormat(tokens)){
                String[] event = FormatString.format("event",tokens);
                if (event != null) {
                    return new Event(event[0], event[1],event[2]);
                } else{
                    throw new DukeException("Failed to add event");
                }
            }
            else{
                Printer.invalidMessage("event");
            }
        }
        catch (IndexOutOfBoundsException ioe){
            Printer.invalidMessage("event");
        } catch (DukeException e) {
            String[] toPrint = {e.toString()};
            Printer.print(toPrint);
        }
        return null;
    }
    public static int markChecker(String[] tokens){
        int key = -1;
        try {
            if (tokens.length == 2) {
                key = Integer.parseInt(tokens[1]);
                key -= 1;
            } else {
                Printer.invalidMark();
            }
        }catch(NumberFormatException nfe){
            Printer.invalidMark();
        }
        return key;
    }
}
