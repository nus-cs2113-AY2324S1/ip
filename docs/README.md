

# DUKE User Guide

Duke is a command line interface (CLI) application that helps you keep track of your tasks. It is cross platform and can run on any machine that has Java installed. This serves as a User Guide for the CS2113 individual project.

---

## Table of Contents

- [DUKE User Guide](#duke-user-guide)
  - [Table of Contents](#table-of-contents)
  - [Quick Start](#quick-start)
  - [Features](#features)
  - [Usage](#usage)
    - [1. `Todo` -  Add a todo task](#1-todo----add-a-todo-task)
    - [2. `Deadline` -  Add a deadline task](#2-deadline----add-a-deadline-task)
    - [3. `Event` -  Add an event task](#3-event----add-an-event-task)
    - [4. `Find` -  Find tasks by keyword](#4-find----find-tasks-by-keyword)
    - [5. `Delete` -  Delete task by index](#5-delete----delete-task-by-index)
    - [6. `Mark` -  Mark task by index](#6-mark----mark-task-by-index)
    - [7. `Unmark` -  Unmark task by index](#7-unmark----unmark-task-by-index)
    - [8. `List` -  List all tasks](#8-list----list-all-tasks)
    - [9. `Bye` -  Exits the program](#9-bye----exits-the-program)
    - [Autosave and load](#autosave-and-load)
    - [Command summary](#command-summary)
---
## Quick Start
1. Ensure you have Java 11 or above installed. If not you can download it [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. Download the latest `duke.jar` from [here]

3. Copy the file to the folder you want to use as the home folder for your Duke.

4. Open the terminal and navigate to the folder where you saved `duke.jar`. 

5. Run the command `java -jar duke.jar` to start the app. 

6. A welcome greeting message by duke will be shown. 

  ```
  ____________________________________________________________
Hello! I'm Duke
What can I do for you?
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
   ```

7. Type the command in the command box ```Enter command: ``` and press Enter to execute. For example typing `list` and pressing Enter will list all the tasks you have.

8. Refer to [Features](#features)

---
## Features 

1. Add todo, deadline and event tasks
2. List and find tasks
3. Mark and unmark tasks as done
4. Delete tasks
5. Autosave and load from disk

---
## Usage

### 1. `Todo` -  Add a todo task

Adds a todo task to the task list.

Example of usage: 

`todo (DESCRIPTION)`

Expected input and outcome:

```
Enter command: todo apple
```

```
Got it. I've added this task: Got it. I've added this task: [T][ ] apple
```

### 2. `Deadline` -  Add a deadline task

Adds a deadline task to the task list.

Example of usage: 

`deadline (DESCRIPTION) /by (DATE)`

Expected input and outcome:

```
Enter command: deadline potato /by Friday
```

```
Got it. I've added this task: [D][ ] potato (Friday)
```

### 3. `Event` -  Add an event task

Adds an event task to the task list.

Example of usage: 

`event (DESCRIPTION) /from (DATE) /to (DATE)`

NOTE: The order of the `/from` and `/to` is important. Ensure that `/from` comes before `/to`.

Expected input and outcome:

```
Enter command: event pear /from Monday /to Thursday
```

```
Got it. I've added this task: [E][ ] pear (Monday to Thursday)
```

### 4. `Find` -  Find tasks by keyword

Prints out all tasks that contains the keyword. Search is case sensitive.

Example of usage: 

`find (KEYWORD)`

Expected input and outcome:

```
Enter command: find apple
```

```
Here are the matching tasks in your list: 
3. [T][ ] apple
```

### 5. `Delete` -  Delete task by index

Deletes the task at the specified index.

Example of usage: 

`delete (INDEX)`

Expected input and outcome:


```
Enter command: delete 3
```

```
Noted. I've removed this task: [T][ ] apple
```

### 6. `Mark` -  Mark task by index

Marks the task at the specified index as done.

Example of usage: 

`mark (INDEX)`

Expected input and outcome:


```
Enter command: mark 3
```

```
Nice! I've marked this task as done: [T][X] apple
```

### 7. `Unmark` -  Unmark task by index

Unmarks the task at the specified index as done.

Example of usage: 

`unmark (INDEX)`

Expected input and outcome:


```
Enter command: unmark 3
```

```
Nice! I've unmarked this task as done: [T][ ] apple
```

### 8. `List` -  List all tasks

Lists all tasks in the task list.

Example of usage: 

`list`

Expected input and outcome:


```
Enter command: list
```

```
Here are the tasks in your list: 
1. [T][ ] potato
2. [T][ ] banana
3. [T][ ] apple

```

### 9. `Bye` -  Exits the program

Exits the program.

Example of usage: 

`bye`

Expected input and outcome:


```
Enter command: bye
```

```
Bye. Hope to see you again soon!

```

### Autosave and load

The program will create a file /data/duke.txt to store the tasks on the disk. The tasks will be loaded from the file when the program starts and will be saved to the file when the program exits.


### Command summary

Command | Format | Description
--- | --- | ---
todo | `todo [description]` | Adds a new todo task with the given description.
deadline | `deadline [description] /by [date/time]` | Adds a new deadline task with the given description and due date/time.
event | `event [description] /from [start time] /to [end time]` | Adds a new event task with the given description and from/to times.
bye | `bye` | Exits the program.
list | `list` | Lists all tasks.
mark | `done [index]` | Marks the specified task as done.
unmark | `undone [index]` | Unmarks the specified task as done.
delete | `delete [index]` | Deletes the specified task.
find | `find [keyword]` | Finds all tasks that contain the specified keyword.