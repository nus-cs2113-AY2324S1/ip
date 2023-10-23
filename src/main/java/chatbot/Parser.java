package chatbot;

import exception.FattyException;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * This is the parser class.
 * It handles all the user inputs and interprets them for the chat bot.
 */

public class Parser {
   /**
    * This is the function that returns the function type
    * Functions that are implemented as private methods in the Duke class are returned as is
    * If tasks are added, "create" is returned instead
    * @param inputMessage
    * @return
    * @throws FattyException
    */
   public String getKeyword(String inputMessage) throws FattyException {
      String keyword = inputMessage.split(" ")[0];

      switch(keyword){
         case "list":
         case "mark":
         case "unmark":
         case "delete":
         case "find":
            return keyword;
         case "todo":
         case "deadline":
         case "event":
            return "create";
         default:
            throw new FattyException("Please enter a valid keyword");
      }
   }

   /**
    * Returns the index for commands that target a specific task
    * @param inputMessage
    * @return
    * @throws FattyException
    * @throws NumberFormatException
    */
   public int getIndex(String inputMessage) throws FattyException, NumberFormatException {
      String[] parts = inputMessage.split(" ");

      if(parts.length != 2) {
         throw new FattyException("Please have correct input format");
      }

      try {
         return Integer.parseInt(parts[1]);
      } catch (NumberFormatException e) {
         throw new FattyException("Please have correct input format");
      }
   }

   public String getSearchField(String inputMessage) {
      return inputMessage.substring(4).trim();
   }

   /**
    * Returns a Task object to the main Duke chatbot class.
    * Handles the creation of the task and parsing of the input message
    * @param inputMessage
    * @return
    * @throws FattyException
    */
   public Task getTask(String inputMessage) throws FattyException {
      String keyword = inputMessage.split(" ")[0];

      switch(keyword){
         case "todo":
            return parseToDo(inputMessage);
         case "deadline":
            return parseDeadline(inputMessage);
         case "event":
            return parseEvent(inputMessage);
         default:
            return new Task("dummy", false);
      }
   }
   private Task parseToDo(String inputMessage){
      return new ToDo(inputMessage, false);
   }

   private Task parseDeadline(String inputMessage) throws FattyException {
      String[] parts = inputMessage.split("/by");

      if(parts.length != 2) {
         throw new FattyException("Missing fields detected");
      }

      return new Deadline(parts, false);
   }

   private Task parseEvent(String inputMessage) throws FattyException {
      String[] parts = inputMessage.split("/from | /to");

      if(parts.length != 3) {
         throw new FattyException("Missing fields detected");
      }

      return new Event(parts, false);
   }
}
