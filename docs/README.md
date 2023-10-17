# User Guide for Careo

## Quick Description

Careo is a simple task tracking software that supports managing todos, events and deadlines.

It's a CLI tool with a focus on simplicity and speed of use.

Careo offers the following commands (hyperlinked to their description):

- [`todo`](#todo---adds-a-todo-type-task-to-the-task-list)
- [`event`](#event---adds-an-event-type-task-to-the-task-list)
- [`deadline`](#deadline---adds-a-deadline-type-task-to-the-task-list)
- [`mark`](#mark---marks-a-stored-task-as-done)
- [`unmark`](#unmark---marks-a-stored-task-as-not-done)
- [`list`](#list---shows-all-stored-tasks)
- [`delete`](#delete---deletes-a-stored-task-from-the-task-list)
- [`find`](#find---searches-for-tasks-matching-a-specified-keyword)
- [`bye`](#bye---exit-careo-and-store-task-list-to-disk)

## Persistence

Careo stores data accross multiple sessions. That means that if you add 
tasks on one day, they will still be there the next time you use Careo. Using 
this feature requires ALWAYS PROPERLY EXITING Careo by using the `bye` command.

## Usage

### Notes about the command format:

Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `/by DATE`, DATE is a parameter which can be used as e.g. `/by Sunday`

### `todo` - Adds a "ToDo" task

Given the description of a ToDo, Careo adds it to the task list and prints a confirmation.

Command format: `todo TODO_DESCRIPTION`

Expected outcome:
Based on the description, a new ToDo is created and added to the task list; i.e. if we first run `todo Buy milk`, this would be the output:

```
    ----------------------------------------------------------------------------
    Got it. I've added this task:
      [T][ ] Buy milk
    Now you have 1 task in the list.
    ----------------------------------------------------------------------------
```


### `event` - Adds an "Event" task

Given the description and from and to dates of an Event, Careo adds it to the task list and prints a confirmation.

Command format: `event EVENT_DESCRIPTION /from FROM_DATE /to TO_DATE`

Expected outcome:
Based on the description, from and to, a new Event is created and added to the task list; i.e. if we first run `event Ballroom /from 8pm /to 11:30`, this would be the output:
```
    ----------------------------------------------------------------------------
    Got it. I've added this task:
      [E][ ] Ballroom (from: 8pm to: 11:30)
    Now you have 1 task in the list.
    ----------------------------------------------------------------------------
```

### `deadline` - Adds a "Deadline" task

Given the description and date of a Deadline, Careo adds it to the task list and prints a confirmation.

Command format: `deadline DEADLINE_DESCRIPTION /by BY_DATE`

Expected outcome:
Based on the description and by date, a new Deadline is created and added to the task list; i.e. if we first run `deadline submit quiz /by today`, this would be the output:

```
    ----------------------------------------------------------------------------
    Got it. I've added this task:
      [D][ ] submit quiz (by: today)
    Now you have 1 task in the list.
    ----------------------------------------------------------------------------
```

### `mark` - Marks a stored task as done

Given the index of a task, Careo marks the task at this index as done and prints a confirmation.
If an invalid index is given, an error message is printed.

Command format: `mark TASK_INDEX`

Expected outcome:
The task at the given index is marked; i.e. if we first ran `todo Buy milk` and then `mark 1`, this would be the output:

```
    ----------------------------------------------------------------------------
    Nice! I've marked this task as done:
      [T][X] Buy milk
    ----------------------------------------------------------------------------
```

### `unmark` - Marks a stored task as not done

Given the index of a task, Careo marks the task at this index as not done and prints a confirmation.
If an invalid index is given, an error message is printed.

Command format: `unmark TASK_INDEX`

Expected outcome:
The task at the given index is marked; i.e. if we first ran `todo Buy milk` and then `mark 1` and then `unmark 1`, this would be the output:

```
    ----------------------------------------------------------------------------
    OK, I've marked this task as not done yet:
      [T][ ] Buy milk
    ----------------------------------------------------------------------------
```

### `list` - Shows all stored tasks

Careo prints out all stored tasks along with their index and type

Command format: `list`

Expected outcome: 
All stored tasks are printed out; i.e. if we first ran 
`todo Buy milk` and `deadline do quiz /by friday`, this would be the output:

```
    ----------------------------------------------------------------------------
    1.[T][ ] Buy milk
    2.[D][ ] do quiz (by: friday)
    ----------------------------------------------------------------------------
```

### `delete` - Deletes a stored task from the task list

Given the index of a task, Careo delets the task at this index and prints a confirmation.
If an invalid index is given, an error message is printed.

Command format: `delete TASK_INDEX`

Expected outcome:
The task at the given index is deleted; i.e. if we first ran `todo Buy milk` and then `delete 1`, this would be the output:

```
    ----------------------------------------------------------------------------
    Noted. I've removed this task:
      [T][ ] Buy milk
    Now you have 0 tasks in the list.
    ----------------------------------------------------------------------------
```

### `find` - Searches for tasks matching a specified keyword

Given a keyword, Careo searches through all tasks to find possible matches 
and returns all tasks whose description contain the specified keyword.

Command format: `find KEYWORD`

Expected outcome:
The task at the given index is marked; i.e. if we first ran `todo Buy milk` and then `deadline view millenium bridge /by soon` and then `find mil`, this would be the output:

```
    ----------------------------------------------------------------------------
    Here are the matching tasks: 
    [T][ ] Buy milk
    [D][ ] view millenium bridge (by: soon)
    ----------------------------------------------------------------------------
```


### `bye` - Exits Careo and stores tasks to disk

Careo prints a goodbye message and saved its current task list to disk.
Using this command is REQUIRED for persistence to function properly.

Command format: `bye`

Expected outcome:
Careo exits correctly; i.e. if we first ran `todo Buy milk` and then `bye`, this would be the output:

```
    ----------------------------------------------------------------------------
    Bye. Hope to see you again soon!
    ----------------------------------------------------------------------------
```
