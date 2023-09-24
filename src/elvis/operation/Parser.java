package elvis.operation;

import java.util.Scanner;

import elvis.command.*;
import elvis.exception.EmptyDeadlineException;
import elvis.exception.EmptyDeleteException;
import elvis.exception.EmptyEventException;
import elvis.exception.EmptyInputException;
import elvis.exception.EmptyListException;
import elvis.exception.EmptyMarkException;
import elvis.exception.EmptyToDoException;
import elvis.exception.EmptyUnmarkException;
import elvis.exception.UnknownInputException;

//For adding, removing, marking, unmarking of Tasks
public class Parser {
    public static void inputTaskFromFile(String lineOfFile) {
        try {
            Parser.taskManager(lineOfFile, true);      //Checks for any errors and handles them
        } catch (UnknownInputException exception) {
            Ui.corruptFileMessagePrinter();
            BootUpShutDown.shutDown();
            System.exit(1);
        }
    }

    public static void inputTaskManually() {
        while (true) {
            String inputBuffer = Ui.inputRequester(); //Requests for Input

            if (inputBuffer.contains("bye")) {  //Program exit
                break;
            } else if (inputBuffer.equals("help")) {
                Ui.printHorizontalLines();
                Ui.helpRequestMessagePrinter();
                Ui.printHorizontalLines();
                Help.helper();
            } else {
                Ui.printHorizontalLines();
                Parser.errorHandler(inputBuffer);      //Checks for any errors and handles them
                Ui.printHorizontalLines();
            }
            TaskList.saver();
        }
        BootUpShutDown.shutDown();
    }

    public static void errorHandler(String inputBuffer) {
        boolean validInput = false;
        try {
            errorChecker(inputBuffer);
            taskManager(inputBuffer, false);
            validInput = true;
        } catch (EmptyInputException exception) {
            Ui.emptyDescriptionMessagePrinter(null);
        } catch (EmptyListException exception) {
            Ui.emptyListMessagePrinter();
            validInput = true;
        } catch (EmptyDeleteException exception) {
            Ui.emptyDescriptionMessagePrinter("delete");
        } catch (EmptyMarkException exception) {
            Ui.emptyDescriptionMessagePrinter("mark");
        } catch (EmptyUnmarkException exception) {
            Ui.emptyDescriptionMessagePrinter("unmark");
        } catch (EmptyToDoException exception) {
            Ui.emptyDescriptionMessagePrinter("todo");
        } catch (EmptyDeadlineException exception) {
            Ui.emptyDescriptionMessagePrinter("deadline");
        } catch (EmptyEventException exception) {
            Ui.emptyDescriptionMessagePrinter("event");
        } catch (UnknownInputException exception) {
            Ui.unknownInputMessagePrinter();
        } finally {
            if (!validInput) {
                Ui.printHorizontalLines();
                Help.helper();
            }
        }
    }

    public static void errorChecker(String inputBuffer) throws EmptyInputException, EmptyListException,
            EmptyToDoException, EmptyMarkException, EmptyUnmarkException, EmptyDeadlineException,
            EmptyEventException, EmptyDeleteException {

        Scanner bufferScanner = new Scanner(inputBuffer);   //Scanner for the buffer
        String firstWord;
        if (!bufferScanner.hasNext()) {                     //Checks for the case when there is no input
            throw new EmptyInputException();
        } else {
            firstWord = bufferScanner.next();
        }

        if (firstWord.equals("list") && TaskList.isArrayEmpty()) {
            throw new EmptyListException();
        }  else if (firstWord.equals("delete") && !bufferScanner.hasNext()) {
            throw new EmptyDeleteException();
        }else if (firstWord.equals("mark") && !bufferScanner.hasNext()) {
            throw new EmptyMarkException();
        } else if (firstWord.equals("unmark") && !bufferScanner.hasNext()) {
            throw new EmptyUnmarkException();
        } else if (firstWord.equals("todo") && !bufferScanner.hasNext()) {
            throw new EmptyToDoException();
        } else if (firstWord.equals("deadline") && !bufferScanner.hasNext()) {
            throw new EmptyDeadlineException();
        } else if (firstWord.equals("event") && !bufferScanner.hasNext()) {
            throw new EmptyEventException();
        }
    }

    public static void taskManager(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        Scanner bufferScanner = new Scanner(inputBuffer);   //Scanner for the buffer
        String firstWord = bufferScanner.next();            //Stores first word in the input
        boolean hasInteger = bufferScanner.hasNextInt();    //Indicates that some integer was input
        int numberInput = -1;                               //Stores the number input
        if (hasInteger) {
            numberInput = bufferScanner.nextInt();
        }

        //Functionalities
        if (inputBuffer.equals("list")) {
            ListCommand.listOut(isFromFile);
        } else if (firstWord.equals("delete") && hasInteger && !bufferScanner.hasNext()) {
            DeleteCommand.taskRemover(numberInput);
        } else if (firstWord.equals("mark") && hasInteger && !bufferScanner.hasNext()) {
            MarkTaskCommand.taskMarker(numberInput);
        } else if (firstWord.equals("unmark") && hasInteger && !bufferScanner.hasNext()) {
            UnmarkTaskCommand.taskUnmarker(numberInput);
        } else if (firstWord.equals("todo")) {
            InsertCommand.insertToDo(inputBuffer, isFromFile);
        } else if (firstWord.equals("deadline")) {
            InsertCommand.insertDeadline(inputBuffer, isFromFile);
        } else if (firstWord.equals("event")) {
            InsertCommand.insertEvent(inputBuffer, isFromFile);
        } else {
            throw new UnknownInputException();
        }
    }
}