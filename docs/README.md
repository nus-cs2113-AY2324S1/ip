# User Guide

Dawson is a **command-line task tracking application** designed to simplify your daily task management. With Dawson, you can add todos, deadlines, and events with ease, and manage them efficiently using a variety of commands. Keep your tasks in check, mark them as completed, and find what you need quickly. Stay on top of your tasks with Dawson, your trusty CLI task tracking app.

- Quick Start
- Features
   - Adding Tasks
      - Add a **Todo** task: `todo`
      - Add a **Deadline** task: `deadline`
      - Add an **Event** task: `event`
   - Task Operations
      - List all tasks: `list`
      - Mark task as done: `mark`
      - Unmark task as not done: `unmark`
      - Delete a task: `delete`
   - Task Filtering
      - Locate tasks by description: `find`
      - Find all tasks with date: `date`
   - Exit Dawson: `bye`
- Command Summary

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest `Dawson.jar` from here.

3. Open a terminal instance and navigate into the folder that contains the downloaded Dawson.jar file.

```bash
cd PATH_TO_JAR_FILE
```

4. Run the jar application with the following command:

```bash
java -jar Dawson.jar
```

You should be greeted by a welcome message from Dawson

## Features

> Things to note in this documentation:
>
> - Commands is **case insensitive**, Dawson will recognise commands upper or lower case or both. Example: `LIST`, `List` or `list` will be recognised as the List command.
>
> - Commands and arguments are separated by **spaces**.
>
> - All **date time format** is in the form of: `dd/MM/yyyy HHmm`. Please specify the full year, for example enter **2023** instead of **23**. The time component is optional, so `dd/MM/yyyy` is also recognised. 
>
> - Date formats are in the form of: `dd/MM/yyyy`. Please specify the full year, for example enter **2023** instead of **23**.

### Add a Todo task: `todo`
Add a todo task with a description.

Format: `todo DESCRIPTION`

### Add a Deadline task: `deadline`
Add a task with a deadline, specifying the due date and time.

Format: `deadline DESCRIPTION /by DUE_DATE_TIME`

`DUE_DATE_TIME` follows the date time format specified above. Time component is set to **23:59** by default if not specified. 

### Add an Event task: `event`
Add an event task with a description and event starting and ending date and time.

Format: `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`

`START_DATE_TIME` and `END_DATE_TIME` follows the date time format specified above. 
- Time component is set to **00:00** by default if not specified for `START_DATE_TIME`. 
- Time component is set to **23:59** by default if not specified for `END_DATE_TIME`. 

### List all tasks: `list`
View the list of tasks along with their statuses.

Format: `list`

### Mark task as done: `mark`
Mark a task as done given a valid index.

Format: `mark INDEX`

### Unmark task as not done: `unmark`
Revert a task's status to undone given a valid index.

Format: `unmark INDEX`

### Delete a task: `delete`
Delete a task from the list given a valid index.

Format: `delete INDEX`

- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

### Locate tasks by description: `find`
Search for tasks containing a specific keyword in their descriptions.

Format: `find QUERY`

### Find all tasks with date: `date`
Filter tasks by a specific date.

Format: `date QUERY_DATE`

### Exit Dawson: `bye`
Exit the Dawson application.

Format: `bye`

## Command Summary

| Action | Format, Examples |
| --- | --- |
| Todo | `todo DESCRIPTION` |
| Deadline | `deadline DESCRIPTION /by DUE_DATE_TIME` |
| Event | `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME` |
| Mark | `mark INDEX` |
| Unmark | `unmark INDEX` |
| Delete | `delete INDEX` |
| Find | `find QUERY` |
| Date | `date QUERY_DATE` |
| List | `list` |
| Exit | `bye` |
