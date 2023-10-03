# User Guide
Alan is a Personal Assistant chatbot application that can help with tracking tasks such as todos, deadlines and events.

* Quick Start
* Features
  * Adding a todo : `todo`
  * Adding a deadline : `deadline`
  * Adding a event : `event`
  * Listing all tasks: `list`
  * Mark task as done : `mark`
  * Mark task as not done : `unmark`
  * Deleting a task : `delete`
  * Finding tasks : `find`
  * Exit program : `bye`

## Quick start
1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest `ip.jar` from [here](https://github.com/DextheChik3n/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your Personal Assistant Chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.
5. If the setup is correct, you should see something like the below as the output:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sup dude! I'm 
 ______     __         ______     __   __    
/\  __ \   /\ \       /\  __ \   /\ "-.\ \   
\ \  __ \  \ \ \____  \ \  __ \  \ \ \-.  \  
 \ \_\ \_\  \ \_____\  \ \_\ \_\  \ \_\\"\_\ 
  \/_/\/_/   \/_____/   \/_/\/_/   \/_/ \/_/ 
 @/
/| 
/ \
What can I do for you, my man?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Input: 

   ```

## Features
> **Notes about command format:**
> - Words in `UPPER_CASE` are the arguments to be supplied by user. <br>
    e.g. `deadline DESCRIPTION /by DATETIME`, `DESCRIPTION` and `DATETIME` are arguments to be specified by user.
> - Parameters need to be in the exact format as specified. <br>
    e.g. `event DESCRIPTION /from DATETIME /to DATETIME`, `/from` must come before `/to`.

### Adding a todo : `todo`

Adds a todo task in the task list.

Format: `todo DESCRIPTION`

Example: <br> `todo read book`

### Adding a deadline : `deadline`

Adds a deadline task in the task list.

Format: `deadline DESCRIPTION /by DATETIME`

Example: <br> `deadline return book /by Feb 14th`

### Adding a event : `event`

Adds a event task in the task list.

Format: `event DESCRIPTION /from DATETIME /to DATETIME`

Example: <br> `event project meeting /from Aug 6th 2pm /to Aug 6th 4pm`

### Listing all tasks : `list`

Lists all the task in the task list.

Format: `list`

Expected outcome:

`list` Prints all the task currently in the task list.

```
Input: list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Dude, check out these tasks on your list:
1. [T][X] read book
2. [D][X] return book (by: June 6th)
3. [E][ ] project meeting (from: Aug 6th 2pm | to: 4pm)
4. [T][X] join sports club
5. [T][ ] borrow book
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### Mark task as done : `mark`

Mark the task status as done.

Format: `mark TASK_INDEX`

- Marks task as done at specified `TASK_INDEX`
- The `TASK_INDEX` refers to the index number shown in the displayed task list. 
The index must be a positive integer 1, 2, 3, …​

Example: <br> `mark 1` Marks the first task in the list as done.

### Mark task as not done : `unmark`

Unmark the task status as not done.

Format: `unmark TASK_INDEX`

- Marks task as not done at specified `TASK_INDEX`
- The `TASK_INDEX` refers to the index number shown in the displayed task list.
  The index must be a positive integer 1, 2, 3, …​

Example: <br> `unmark 1` Marks the first task in the list as not done.

### Deleting a task : `delete`

Deletes the task in the task list.

Format: `delete TASK_INDEX`

- Deletes task at specified `TASK_INDEX`
- The `TASK_INDEX` refers to the index number shown in the displayed task list.
  The index must be a positive integer 1, 2, 3, …​

Example: <br> `list` followed by `delete 2` deletes the 2nd task in the task list.

### Finding tasks : `find`

Finds tasks whose description matches the given keyword.

Format: `find KEYWORD`

Example: <br> `find book` will find tasks with the keyword `book` in the list.

```
Input: find book
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Dude check it out, here are the matching tasks in your list:
1. [T][X] read book
2. [D][X] return book (by: June 6th)
3. [T][ ] borrow book
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### Exit program : `bye`

Exits the chatbot program.

Format: `bye`
