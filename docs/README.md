# User Guide
Sun is a desktop app for managing tasks, 
optimized for use via a Command Line Interface (CLI).

## Features

### Feature-Adding a task

Adds a task to the current list of tasks.
There are three types of task: todo, deadline and event.

### Feature-Listing all tasks

Shows a list of all tasks.

### Feature-Find tasks by keyword

shows all tasks containing the given keyword

### Feature-Deleting a task

Deletes the specified task from the list of tasks.

### Marking and unMarking a task as done

Marks and unMarks the specified task from the list of tasks as done.

## Quick start

1. Download the latest JAR file (`ip.jar`).
2. Copy the JAR file to the folder you want to use as the **home folder** for your Task Manager.
3. Open a command terminal, move into the folder you put the jar file in.
4. Enter the following command to run the program:

   ```
   java -jar ip.jar
   ```

5. You should see a welcome message as shown below:
   ```   
   Hello from
    ____
   | ___| _   _  ______
   | \__ | | | || /--\ |
    \___|| |_| || |  | |
   /____/ \__,_||_|  |_|

   ____________________________________________________________
   What can I do for you?
   ____________________________________________________________
   ```

## Usage

### `todo` - Adds a todo to the current list of tasks.
Format: `todo DESCRIPTION​`  
Examples: 
```
todo buy coffee
```
Expected outcome: 
```
____________________________________________________________
Got it. I've added this task:
  [T][ ] buy coffee
Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Adds a deadline to the current list of tasks.
Format: `deadline DESCRIPTION /by DATETIME` 

The format of datetime can be:

- dd/mm/yyyy time
- yyyy/mm/dd time
- dd-mm-yyyy time
- yyyy-mm-dd time

Examples:
```
deadline return books /by 06/10/2023 1200
```
Expected outcome:
```
____________________________________________________________
Got it. I've added this task:
  [D][ ] return books (by: 12.00pm 06 Oct, 2023)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Adds an event to the current list of tasks.
Format: `event DESCRIPTION /from START_DATETIME /to END_DATETIME`  

The format of datetime can be:

- dd/mm/yyyy time
- yyyy/mm/dd time
- dd-mm-yyyy time
- yyyy-mm-dd time

Examples:
```
event CS2133 lecture /from 19/10/2023 1000 /to 19/10/2023 1200
```
Expected outcome:
```
____________________________________________________________
Got it. I've added this task:
  [E][ ] CS2133 lecture (from: 10.00am 19 Oct, 2023to: 12.00pm 19 Oct, 2023)
Now you have 3 tasks in the list.
____________________________________________________________
```
### `list` - Lists all tasks
Format: `list`  
Examples:
```
list
```
Expected outcome:
```
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] buy coffee
2. [D][ ] return books (by: 12.00pm 06 Oct, 2023)
3. [E][ ] CS2133 lecture (from: 10.00am 19 Oct, 2023to: 12.00pm 19 Oct, 2023)
____________________________________________________________
```
### `mark` - Marks a task as done.
Format: `mark INDEX`  

The index refers to the index number shown in the displayed list of tasks.

Examples:
```
mark 2
```
Expected outcome:
```
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return books (by: 12.00pm 06 Oct, 2023)
____________________________________________________________
```
### `unmark` - UnMarks a task as done.
Format: `unmark INDEX`  

The index refers to the index number shown in the displayed list of tasks.

Examples:
```
unmark 2
```
Expected outcome:
```
____________________________________________________________
OK, I've marked this task as not done yet:
  [D][ ] return books (by: 12.00pm 06 Oct, 2023)
____________________________________________________________
```
### `find` - Filter tasks by keyword .
Format: `find KEYWORD`  
Examples:
```
find book
```
Expected outcome:
```
____________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] return books (by: 12.00pm 06 Oct, 2023)
____________________________________________________________
```
### `delete` - Deletes a task.
Format: `delete INDEX`  

- The index refers to the index number shown in the displayed list of tasks.

Examples:
```
delete 2
```
Expected outcome:
```
____________________________________________________________
Noted. I've removed this task:
  [D][ ] return books (by: 12.00pm 06 Oct, 2023)
Now you have 2 tasks in the list.
____________________________________________________________
```
### `bye` - closes the Sun.
Format: `bye`  
Examples:
```
bye
```
Expected outcome:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```