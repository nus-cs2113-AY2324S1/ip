# User Guide

Elvis is a chatbot for managing todo, deadline, event for use via a Command Line Interface (CLI).

- [Quick start](https://woodenclock.github.io/ip/#quick-start)
- [Features](https://woodenclock.github.io/ip/#features)
    - [Viewing help: `help`](https://woodenclock.github.io/ip/#1-viewing-help--help)
    - [Adding a todo: `todo`](https://woodenclock.github.io/ip/#2-adding-a-todo-todo)
    - [Adding a deadline: `deadline`](https://woodenclock.github.io/ip/#2-adding-a-deadline-deadline)
    - [Adding an event: `event`](https://woodenclock.github.io/ip/#3-adding-an-event-event)
    - [Listing all tasks: `list`](https://woodenclock.github.io/ip/#4-listing-all-tasks-list)
    - [Finding a task: `find`](https://woodenclock.github.io/ip/#5-finding-a-task-find)
    - [Deleting a task: `delete`](https://woodenclock.github.io/ip/#6-deleting-a-task-delete)
    - [Marking a task: `mark`](https://woodenclock.github.io/ip/#7-marking-a-task-mark)
    - [Unmarking a task: `unmark`](https://woodenclock.github.io/ip/#8-unmarking-a-task-unmark)
    - [Exiting program: `bye`](https://woodenclock.github.io/ip/#9-exiting-program-bye)
    - [Saving the Tasklist](https://woodenclock.github.io/ip/#10-saving-the-tasklist)
- [Command summary](https://woodenclock.github.io/ip/#command-summary)

  
--------------------------------------------------------------------------------------------------------------------------------------


## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
   
1. Download the latest `Elvis.jar` from [here](https://github.com/woodenclock/ip/releases).

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
1. Type commands below the horizontal line and press Enter to execute it. (e.g. typing `help` and pressing `Enter` will open the help page).
Some example commands you can try:
- `list` : Lists all contacts.
- `bye` : Exits the app.
  
1. Refer to the [Features](https://woodenclock.github.io/ip/#features) below for details of each command.

[back_to_top](https://woodenclock.github.io/ip/)


--------------------------------------------------------------------------------------------------------------------------------------


## Features
:information_source:**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo TASK_TO_DO`, `TASK_TO_DO` is a parameter which can be used as `todo read books`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.


### 1. Viewing help : `help`

Shows the help page.

Format: `help`

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
[back_to_top](https://woodenclock.github.io/ip/)


### 2. Adding a todo: `todo`

Adds a todo to the Tasklist.

Format: `todo TASK_TO_DO`

Example: `todo read books`

```
   todo read books
   ____________________________________________________________
   Got it. I've added this task:
   [T][ ] read books
   Now you have 1 task(s) in the list.
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 3. Adding a deadline: `deadline`

Adds a deadline to the Tasklist.

Format: `deadline TASK_TO_DO /by DATE TIME`

**Warning**:
- Ensure there is only 1 whitespace between date and time
- Year, month, date must be divided by "/" or "-" (e.g. 2021/06/28 OR 2021-06-28)
- Date can be one of following formats:
  - yyyymmdd
  - yyyymmd
  - yyyymdd
  - yyyymd
  - ddmmyyyy
  - dmmyyyy
  - ddmyyyy
  - dmyyyy
- Time must be 24-Hr "HHmm" format (e.g. 1300 OR 13:00)

Example: `deadline return book /by 2023/06/28 1300`

```
   ____________________________________________________________
   deadline return book /by 2023/06/28 1300
   ____________________________________________________________
   Got it. I've added this task:
   [D][ ] return book (by: 28/06/2023 1300)
   Now you have 2 task(s) in the list.
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 4. Adding an event: `event`

Adds an event to the Tasklist.

Format: `event TASK_TO_DO /from DATE TIME /to DATE TIME`

**Warning**:
- Ensure there is only 1 whitespace between date and time
- Year, month, date must be divided by "/" or "-" (e.g. 2021/06/28 OR 2021-06-28)
- Date can be one of following formats:
  - yyyymmdd
  - yyyymmd
  - yyyymdd
  - yyyymd
  - ddmmyyyy
  - dmmyyyy
  - ddmyyyy
  - dmyyyy
- Time must be 24-Hr "HHmm" format (e.g. 1300 OR 13:00)

Example: `event project meeting /from 2022-08-06 1300 /to 6-8-2022 1500`

```
   ____________________________________________________________
   event project meeting /from 2022-08-06 1300 /to 6-8-2022 1500       
   ____________________________________________________________
   Got it. I've added this task:
   [E][ ] project meeting (from: 06/08/2022 1300 to: 06/08/2022 1500)
   Now you have 3 task(s) in the list.
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 5. Listing all tasks: `list`

Lists all tasks in the Tasklist.

Format: `list`

Legend:
- A todo shows up as `[T][ ]...`, where T stands for todo.
- A deadline shows up as `[D][ ]...`, where D stands for deadline.
- An event shows up as `[E][ ]...`, where E stands for event.
- The second bracket shows if the task is done, with an `X` (e.g. `[T][X]...` ).

```
   ____________________________________________________________
   list
   ____________________________________________________________
   Here are the tasks in your list:
   1.[T][ ] read books
   2.[D][ ] return book (by: 28/06/2023 1300)
   3.[E][ ] project meeting (from: 06/08/2022 1300 to: 06/08/2022 1500)
   Now you have 3 task(s) in the list.
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 6. Finding a task: `find`

Finds all tasks in the Tasklist that matches the keyword.

Format: `find`

Example: `find book`

```
   ____________________________________________________________
   find book
   ____________________________________________________________
   Here are the matching tasks in your list:
   1.[T][ ] read books
   2.[D][ ] return book (by: 28/06/2023 1300)
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 7. Deleting a task: `delete`

Deletes a specific task in the Tasklist.

Format: `delete INDEX`

Example: `delete 3`

```
   ____________________________________________________________
   delete 3
   ____________________________________________________________
   Noted. I've removed this task:
   [E][ ] project meeting (from: 06/08/2022 1300 to: 06/08/2022 1500)
   Now you have 2 task(s) in the list.
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 8. Marking a task: `mark`

Marks a specific task in the Tasklist.

Format: `mark INDEX`

Example: `mark 2`

```
   ____________________________________________________________
   mark 2
   ____________________________________________________________
   Nice! I've marked this task as done:
   [D][X] return book (by: 28/06/2023 1300)
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 9. Unmarking a task: `unmark`

Unmarks a specific task in the Tasklist.

Format: `unmark INDEX`

Example: `unmark 2`

```
   ____________________________________________________________
   unmark 2
   ____________________________________________________________
   OK, I've marked this task as not done yet:
   [D][ ] return book (by: 28/06/2023 1300)
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 10. Exiting program: `bye`

Exits the program

Format: `bye`

```
   ____________________________________________________________
   bye
   ____________________________________________________________
   Bye. Hope to see you again soon!
   ____________________________________________________________
```
[back_to_top](https://woodenclock.github.io/ip/)


### 11. Saving the Tasklist

Tasklist data are saved in the hard disk automatically after every valid command changes the data. 
There is no need to save manually.

[back_to_top](https://woodenclock.github.io/ip/)


--------------------------------------------------------------------------------------------------------------------------------------


## FAQ

Q: Why do I get the `Invalid Date/Time input` error?
A: Ensure there is only 1 whitespace between date and time.

Q: I found a bug! What do I do?
A: Great! Please contact [Min](https://github.com/woodenclock), your help is greatly appreciated!

[back_to_top](https://woodenclock.github.io/ip/)


--------------------------------------------------------------------------------------------------------------------------------------


## Command summary

Action | Format, Examples
------ | -----------------
help | `help`
todo | Format: `todo TASK_TO_DO` Example: `todo read books`
deadline | Format: `deadline TASK_TO_DO /by DATE TIME` Example: `deadline return book /by 2023/06/28 1300`
event | Format: `event TASK_TO_DO /from DATE TIME /to DATE TIME` Example: `event project meeting /from 2022-08-06 1300 /to 6-8-2022 1500`
list | Format: `list`
find | Format: `find` Example: `find book`
delete | Format: `delete INDEX` Example: `delete 3`
mark | Format: `mark INDEX` Example: `mark 2`
unmark | Format: `unmark INDEX` Example: `unmark 2`
bye | Format: `unmark INDEX`

[back_to_top](https://woodenclock.github.io/ip/)
