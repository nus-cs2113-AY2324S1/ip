# Skippy Chat Bot - User Guide

## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.

2. Download the latest version of Skippy from [here](https://github.com/J0shuaLeong/ip/releases/latest). Note that you do not need to download the zip files for the application.

3. You should find the jar file in your default downloads folder. Please place the jar file into a separate folder that will be used as your `home folder`.

4. Open a command terminal, and change the current working directory to the `home folder`.

5. Type ```java -jar ip.jar``` in the terminal to open the application. You should see the welcome message "Hi! I am Skippy the BunBun!" on the next line.

6. The application is now ready for you to use! Type `help` to see a list of commands that you will be able to use in the application.

## Features

* [Viewing help : `help`](#View-Help-Guide-help)

* [Exiting the application : `bye`](#Exiting-the-application-bye)

* [Viewing list of tasks : `list`](#viewing-list-of-all-tasks-list)

* [Adding tasks](#adding-tasks)

  * [Adding ToDo task : `todo`](#Adding-Todo-Task-todo)

  * [Adding Deadline task : `deadline`](#Adding-Deadline-Task-deadline)

  * [Adding Event task : `event`](#Adding-Event-event)

* [Marking task as done : `mark`](#marking-a-task-as-done-mark)

* [Marking task as not done : `unmark`](#marking-task-as-not-done-unmark)

* [Deleting a task : `delete`](#deleting-a-task-delete)

* [Finding a task : `find`](#finding-a-task-find)


### View Help Guide: `help`
Shows the list of commands with the command format and short explanation.

**Example of usage:**

```
help
```

**Expected output:**
```
Hello, I am Skippy the bunny and I'm your personal assistant.
    1. list: Lists all tasks
    2. todo <name>: Adds a ToDo task.
    3. deadline <name> /by <by when>: Adds a deadline task with specified deadline.
    4. event <name> /from <from when> /to <to when>: Adds an event with specified time period.
    5. mark <task number>: Marks the specified task as done.
    6. unmark <task number>: Marks the specified task as not done.
    7. delete <task number>: Deletes the specified task from the list.
    8. find <keyword>: Finds and lists the tasks that contains the keyword in its name.
    9. bye: Exits the program.
```

### Exiting the application: `bye`
Exits Skippy Chat Bot application.

**Example of usage:**

```
bye
```

**Expected output:**
```
Saving tasks...
Goodbye! Hope to see you again soon!
```

### Viewing List of All Tasks: `list`
Lists all the tasks.

Format: `list`

**Example of usage:**
```
list
```

**Expected output:**
```
Here are the tasks in your list:
1. [T][ ] feed bunny
2. [D][ ] renew pet license (by: 5 Oct 2023 8am)
3. [E][ ] pet expo (from: 20 Oct 2023 12pm to: 21 Oct 2023 6pm)
```

The first `[]` is the task type. `[T]` denotes a ToDo task, `[D]` denotes a Deadline task, `[E]` denotes an Event task.

The second `[]` is the completion status of the task. `[X]` denotes that task is completed, `[ ]` denotes that task is not completed.

The string not enclosed by brackets is the name, following strings enclosed by brackets are the arguments of the keyword, indicated by the keyword before `:`.

**TLDR**
List format: `[Task Type][Completion status] name (keyword: argument of keyword) (keyword: argument of keyword)`

### Adding Tasks

Command format:

* All keywords must be present. E.g. `/by`, `/from`, `/to`.

* All arguments cannot be blank. E.g. `<name>`, `<by when>`

* All arguments cannot contain the character `|`.

* If any of the above rules are violated, an error message will be shown and no task will be added to the list.

### Adding Todo Task: `todo`
Creates a todo task.

Format: `todo <name>`

**Example of usage:**

```
todo feed bunny
```

**Expected output:**
```
Got it. I've added this task:
 [T][ ] feed bunny
Now you have 1 tasks in the list.
```

### Adding Deadline Task: `deadline`
Creates a deadline task.

Format: `deadline <name> /by <by when>`

**Example of usage:**

```
deadline renew pet license /by 5 Oct 2023 8am
```

**Expected output:**
```
Got it. I've added this task:
 [D][ ] renew pet license (by: 5 Oct 2023 8am)
Now you have 2 tasks in the list.
```

### Adding Event: `event`
Creates an event.

Format: `event <name> /from <from when> /to <to when>`

**Example of usage:**

```
event pet expo /from 20 Oct 2023 12pm /to 21 Oct 2023 6pm
```

**Expected output:**
```
Got it. I've added this task:
 [E][ ] pet expo (from: 20 Oct 2023 12pm to: 21 Oct 2023 6pm)
Now you have 3 tasks in the list.
```

### Marking a Task as done: `mark`
Marks a task as done.

Format: `mark <task number>`

**Example of usage:**

```
mark 2
```

**Expected output:**
```
Ok! I've marked this task as done:
 [D][X] renew pet license (by: 5 Oct 2023 8am)
```
Note: Error messages will be shown if `<task number>` is invalid. E.g. Negative task number, task number bigger than number of existing tasks.


### Marking Task as not done: `unmark`
Marks a task as undone.

Format: `unmark <task number>`

**Example of usage:**

```
unmark 2
```

**Expected output:**
```
OK, I've marked this task as not done yet:
 [D][ ] renew pet license (by: 5 Oct 2023 8am)
```
Note: Error messages will be shown if `<task number>` is invalid. E.g. Negative task number, task number bigger than number of existing tasks.

### Deleting a Task: `delete`
Deletes a task.

Format: `delete <task number>`

**Example of usage:**
```
delete 3
```

**Expected outcome:**
````
Got it. I've removed this task:
 [E][ ] pet expo (from: 20 Oct 2023 12pm to: 21 Oct 2023 6pm)
Now you have 2 tasks in the list.
````
Note: Error messages will be shown if `<task number>` is invalid. E.g. Negative task number, task number bigger than number of existing tasks.

### Finding a Task: `find`
Searches tasks by keyword in the task list.

Format: `find <keyword>`

**Example of usage:**
```
find pet
```

**Expected outcome:**
````
These tasks contain the keyword read:
1. [D][ ] renew pet license (by: 5 Oct 2023 8am)
2. [E][ ] pet expo (from: 20 Oct 2023 12pm to: 21 Oct 2023 6pm)
````
