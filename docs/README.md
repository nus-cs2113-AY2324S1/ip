# User Guide
This is a user guide for CS2113 iP by charkty.

## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
   - [Listing all tasks: `list`](#listing-all-tasks-list)
   - [Adding a todo task: `todo`](#adding-a-todo-task-todo)
   - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
   - [Adding an event task: `event`](#adding-an-event-task-event)
   - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
   - [Marking a task as undone: `unmark`](#marking-a-task-as-undone-unmark)
   - [Deleting a task: `delete`](#deleting-a-task-delete)
   - [Finding a task by keyword: `find`](#finding-a-task-by-keyword-find)
   - [Exiting the program: `bye`](#exiting-the-program-bye)
3. [FAQ](#faq)

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/charkty/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Alice.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar
   ip.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the [Features](#features) below for details of each command.

## Features

### Listing all tasks: `list`

Displays a list of all current tasks in the task list in sequential order. Tasks are updated andsaved into the user's hard disk each time user makes edits.

Format: `list`

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`
- Adds a Task with the description `TASK_DESCRIPTION` to the task list.
- The `TASK_DESCRIPTION` should not be empty.

Examples:
- `todo keep laundry`
- `todo Mark assignments`

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

- Adds a Task with the description `TASK_DESCRIPTION` and deadline `DEADLINE` to the task list.
- The `TASK_DESCRIPTION` should not be empty.
- The `/by` keyword is required.
- The `DEADLINE` should be in the format `dd-MM-yyyy HHmm`.

Examples:
- `deadline return book /by 14-10-2023 2359`Creates a deadline task of description "return book" by 11.59pm on 14 Oct 2023.
- `deadline Finish homework /by 15-03-2023 1300` Creates a deadline task of description "Finish homework" by 1pm on 16 March 2023.

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event TASK_DESCRIPTION /from START_TIME /to END_TIME`

- Adds a Task with the description `TASK_DESCRIPTION`, start time `START_TIME`
  and end time `END_TIME` to the task list.
- `/from` has to come before `/to`.
- The `START_TIME` and `END_TIME` should be in the format `dd-MM-yyyy HHmm`.


Examples:
- `event badminton training /from 10-12-2023 1400 /to 10-12-2023 1500` Creates an event task with description "badminton training", from 2-3pm on 10 Dec 2023.
- `event NUS open house /from 11-12-2023 1400 /to 11-12-2023 1500` Creates an event task with description "NUS open house", from 2-3pm on 11 Dec 2023.

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark TASK_INDEX`

- Marks the task at the specified `TASK_INDEX` of the list as done.
- The `TASK_INDEX` should be a positive integer between 1 to the latest task count.

Examples:
- `mark 1` marks the first task in the list as done.
- `mark 2` marks the second task in the list as done.

### Marking a task as undone: `unmark`

Marks a task as undone.

Format: `unmark TASK_INDEX`

- Marks the task at the specified `TASK_INDEX` of the list as not done.
- The `TASK_INDEX` should be a positive integer between 1 to the latest task count.

Examples:
- `unmark 1` marks the first task in the list as not done.
- `unmark 2` marks the second task in the list as not done.

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete TASK_INDEX`

- Deletes the task at the specified `TASK_INDEX` of the list.
- The `TASK_INDEX` should be a positive integer between 1 to the latest task count.

Examples:
- `delete 1` deletes the first task in the list.
- `delete 2` deletes the second task in the list.

### Finding a task by keyword: `find`

Finds tasks whose description contains the given keyword.

Format: `find KEYWORD`

- Finds tasks whose description contains the given `KEYWORD`.
- The search is *case-insensitive*. e.g `book` will match `Book`
- The `KEYWORD` should only be a single word.
- The search is only for task descriptions, date and time are excluded.

Examples:
- `find training` returns tasks with descriptions containing the word "training".

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## FAQ

**Q**: How do I save my data in my hard disk?  
**A**: Alice saves your data in your hard disk automatically after each command.
