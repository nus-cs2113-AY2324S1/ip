# User Guide for RC chatbot

RC chatbot is a desktop app for **managing tasks, optimised for use via a Command Line Interface(CLI).**

## Quick Start

1. Ensure you have `Java 11` or above installed on your computer.
2. Download the latest `RC.jar` from [here](https://github.com/ryan1604/ip/releases/download/v0.2/RC.jar).
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar RC.jar` command to run the chatbot.
5. Refer to the Features below for details of each command.


## Features
**Notes about the command format:**
- Words in `UPPER_CASE` are the parameters to be supplied by the user.

  eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

### Adding a todo task: `todo`
Adds a todo task which is not time-sensitive.

Format: `todo DESCRIPTION`

Example: `todo read book`

Example outcome:
```
    Got it. I've added this task:
      [T][ ] read book
    Now you have 3 tasks in the list.
```
### Adding a deadline task: `deadline`
Adds a deadline task that has a due date.

Format: `deadline DESCRIPTION /by DATE TIME`
- The date follows yyyy-M-d format eg. 2023-3-12 means March 12 2023.
- The time follows HHmm in 24-hr format eg. 1200 means 1200pm.

Example: `deadline return book /by 2023-3-3 1700`

Example outcome:
```
    Got it. I've added this task:
      [D][ ] return book (by: Mar 3 2023 1700PM)
    Now you have 4 tasks in the list.
```
### Adding a event task: `event`
Adds a event task that has a start date-time and end date-time.

Format: `event DESCRIPTION /from START_DATE START_TIME /to END_DATE END_TIME
- The date follows yyyy-M-d format eg. 2023-3-12 means March 12 2023.
- The time follows HHmm in 24-hr format eg. 1200 means 1200pm.
- The start date and time should be earlier than the end date and time.

Example: `event attend lecture /from 2023-10-12 1400 /to 2023-10-12 1600`

Example outcome:
```
    Got it. I've added this task:
      [E][ ] attend lecture (from: Oct 12 2023 1400PM to: Oct 12 2023 1600PM)
    Now you have 5 tasks in the list.
```
### List all tasks: `list`
Shows a list of all tasks.

Format: `list`
### Mark a task: `mark`
Marks a task as done based on the specified index.

Format: `mark INDEX`
- The index refers to the index number shown in the displayed task list.

Example: `mark 3`

Example outcome:
```
    Nice! I've marked this task as done:
      [T][X] read book
```
### Unmark a task: `unmark`
Unmarks a task as done based on the specified index.

Format: `mark INDEX`
- The index refers to the index number shown in the displayed task list.

Example: `unmark 3`

Example outcome:
```
    OK, I've marked this task as not done yet:
      [T][ ] read book
```
### Delete a task: `delete`
Deletes a task based on the specified index.

Format: `delete INDEX`
- The index refers to the index number shown in the displayed task list.

Example: `delete 3`

Example outcome:
```
    Noted. I've removed the following task:
      [T][ ] read book
    Now you have 4 tasks in the list.
```
### Find a task: `find`
Finds all tasks that matches the keyword.

Format: `find KEYWORD`
- The search is case-insensitive eg. `read book` will match `Read Book`.

Example: `find lecture`

Example outcome:
```
    Here are the matching tasks in your list:
    1.[E][ ] attend lecture (from: Oct 12 2023 1400PM to: Oct 12 2023 1600PM)
```
### Exiting the program: `exit`
Exits the program.

Format: `bye`
