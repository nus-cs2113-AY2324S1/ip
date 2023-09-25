# User Guide

Elvis is a chatbot for managing todo, deadline, event for use via a Command Line Interface (CLI) 

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
   
1. Download the latest `addressbook.jar` from [here](https://github.com/woodenclock/ip/releases).

1. Copy the file to the folder you want to use as the home folder for your Elvis.

1. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Elvis.jar command to run the application.
```
             _____                      _____            _____                      _____                      _____          
            /\    \                    /\    \          /\    \                    /\    \                    /\    \         
           /::\    \                  /::\____\        /::\____\                  /::\    \                  /::\    \        
          /::::\    \                /:::/    /       /:::/    /                  \:::\    \                /::::\    \       
         /::::::\    \              /:::/    /       /:::/    /                    \:::\    \              /::::::\    \      
        /:::/\:::\    \            /:::/    /       /:::/    /                      \:::\    \            /:::/\:::\    \     
       /:::/__\:::\    \          /:::/    /       /:::/____/                        \:::\    \          /:::/__\:::\    \    
      /::::\   \:::\    \        /:::/    /        |::|    |                         /::::\    \         \:::\   \:::\    \
     /::::::\   \:::\    \      /:::/    /         |::|    |     _____      ____    /::::::\    \      ___\:::\   \:::\    \
    /:::/\:::\   \:::\    \    /:::/    /          |::|    |    /\    \    /\   \  /:::/\:::\    \    /\   \:::\   \:::\    \
   /:::/__\:::\   \:::\____\  /:::/____/           |::|    |   /::\____\  /::\   \/:::/  \:::\____\  /::\   \:::\   \:::\____\
   \:::\   \:::\   \::/    /  \:::\    \           |::|    |  /:::/    /  \:::\  /:::/    \::/    /  \:::\   \:::\   \::/    /
    \:::\   \:::\   \/____/    \:::\    \          |::|    | /:::/    /    \:::\/:::/    / \/____/    \:::\   \:::\   \/____/
     \:::\   \:::\    \         \:::\    \         |::|____|/:::/    /      \::::::/    /              \:::\   \:::\    \     
      \:::\   \:::\____\         \:::\    \        |:::::::::::/    /        \::::/____/                \:::\   \:::\____\    
       \:::\   \::/    /          \:::\    \       \::::::::::/____/          \:::\    \                 \:::\  /:::/    /    
        \:::\   \/____/            \:::\    \       ~~~~~~~~~~                 \:::\    \                 \:::\/:::/    /     
         \:::\    \                 \:::\    \                                  \:::\    \                 \::::::/    /      
          \:::\____\                 \:::\____\                                  \:::\____\                 \::::/    /       
           \::/    /                  \::/    /                                   \::/    /                  \::/    /        
            \/____/                    \/____/                                     \/____/                    \/____/
    
    Checking if "tasks.txt" already exists...
    File does not exist.
    Creating new file...
    File created successfully.
    ____________________________________________________________
    Hello! I'm ELVIS
    What can I do for you?
    ____________________________________________________________
```
1. Type commands below the horizontal line and press Enter to execute it. e.g. typing `help` and pressing `Enter` will open the help page.
Some example commands you can try:
- `list` : Lists all contacts.
- `bye` : Exits the app.

1. Refer to the [Features](https://woodenclock.github.io/ip/#features) below for details of each command.
   
--------------------------------------------------------------------------------------------------------------------

## Features
**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo TASK_TO_DO`, `TASK_TO_DO` is a parameter which can be used as `todo read books`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

### 1. Viewing help : `help`

Shows the help page.
```
   ____________________________________________________________
   help
   ____________________________________________________________
   No worries, I'm here to help!
   ____________________________________________________________
   
   
   ---------------------------HELP PAGE----------------------------
   
   1. To add a todo, type "todo + {description}"
      Example: todo borrow book
       ____________________________________________________________
      Got it. I've added this task:
      [T][ ] borrow book
      Now you have 5 tasks in the list.
       ____________________________________________________________
   
   2. To list out all the tasks, type "list"
      Example: list
       ____________________________________________________________
      Here are the tasks in your list:
      1.[T][X] read book
      2.[D][ ] return book (by: June 6th)
      3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
      4.[T][X] join sports club
      5.[T][ ] borrow book
       ____________________________________________________________
   
   3. To add a deadline, type "deadline + {description} + /by + {date time}"
      Example: deadline return book /by Sunday
       ____________________________________________________________
      Got it. I've added this task:
      [D][ ] return book (by: 2023-09-23 18:00)
      Now you have 6 tasks in the list.
       ____________________________________________________________
   
   4. To add an event, type "deadline + {description} + /from + {date time} + /to {date time}"
      Example: event project meeting /from Mon 2pm /to 4pm
       ____________________________________________________________
      Got it. I've added this task:
      [E][ ] project meeting (from: 2023-09-23 18:00 to: 2023-09-23 19:00)
      Now you have 7 tasks in the list.
       ____________________________________________________________
   
   5. {date time} should be written in this format:
       ____________________________________________________________
      Date: dd/mm/yyyy OR yyyy-mm-dd
      Example: 09/11/2023 OR 2023-11-09
      NOTE: There should be a spacing between the date and time
      Time: HHmm OR HH:mm in 24-Hr format
      Example: 1800 OR 18:00
       ____________________________________________________________
   
   6. To delete a task, type "delete + {task number}"
      Example: delete 1
       ____________________________________________________________
      Noted. I've removed this task:
      [E][ ] project meeting (from: Mon 2pm to: 4pm)
      Now you have 6 tasks in the list.
       ____________________________________________________________
   
   -------------------------HELP PAGE END--------------------------
```
Format: `help`

--------------------------------------------------------------------------------------------------------------------

### Adding a todo: `todo`

Adds a todo to the Tasklist.

Format: `todo TASK_TO_DO`
```
   todo read books
   ____________________________________________________________
   Got it. I've added this task:
   [T][ ] read books
   Now you have 1 task(s) in the list.
   ____________________________________________________________
```

Examples:
* `todo read books`






























## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
