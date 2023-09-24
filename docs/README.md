# User Guide

# BotName: JARVIS

## Features 

### Feature-todo

Adds a task of type todo into the list.

### Feature-deadline

Adds a task of type deadline into the list.

### Feature-event

Adds a task of type event into the list.

### Feature-list

Displays current list of tasks.

### Feature-mark

Marks a task as done.

### Feature-unmmark

Marks a task as not done.

### Feature-delete

Removes a task from task list.

### Feature-find

Search for a particular task in the list.

### Feature-bye

Exits programme.

## Usage

### `todo` - Adds task of type todo

Adds a task of type todo into the task-list to be saved.

Example of usage: 

`todo cry`

Expected outcome:

todo task is added successfully into task-list.

```
added: cry
Now you have 1 tasks in the list.
```
### `deadline` - Add task of type deadline

Adds a task of type deadline with a due date into the task list successfully.

Example of usage:

`deadline sleep /by sunday`

Expected outcome:

task of type deadline is successfully added into the task-list.

```
added: sleep (by: sunday)
Now you have 2 tasks in your list.
```
### `event` - Add task of type event

Adds a task of type event with a due date into the task list successfully.

Example of usage:

`event cs2113 lecture /from 4pm /to 6pm`

Expected outcome:

task of type event is successfully added into the task-list.

```
added:  cs2113 lecture (from: 4pm to: 6pm)
Now you have 3 tasks in the list.
```
### `list` - Displays all tasks

List out all existing tasks previously entered into the bot.

Example of usage:

`list`

Expected outcome:

Successfully prints out all tasks in tasklist.

```
Here are the tasks in your list
1.[T][ ]  cry
2.[D][ ]  sleep (by:  sunday)
3.[E][ ]  cs2113 lecture (from: 4pm to:  6pm)
```

### `mark` - Mark task as completed

Marks a task as done based on task number of task in the list.

Example of usage:

`mark 1`

Expected outcome:

Successfully marks task as done based on user input.

```
Task Completed!
[X]  cry
```

### `unmark` - Unmark a completed task

Unmark a task as done based on task number of task in the list.

Example of usage:

`unmark 1`

Expected outcome:

Successfully unmark task as done based on user input.

```
Task marked as uncompleted!
[ ]  cry
```

### `delete` - Remove task from task-list

Removes a task from the list based on task number of task in the list.

Example of usage:

`delete 1`

Expected outcome:

Successfully removes task based on user input.

```
Task Removed
[T][ ]  cry
Now you have 2 tasks in the list.
```

### `find` - Search for specific task in list

Searches and prints out list of tasks with description matching user input.

Example of usage:

`find lecture`

Expected outcome:

Successfully prints task found based on user input.

```
Here are the matching tasks in your list: 
1.[E][ ]  cs2113 lecture (from: 4pm to:  6pm)
```
### `bye` - Terminates programme

End the programme

Example of usage:

`bye`

Expected outcome:

Successfully closes the programme.

```
Bye. Hope to see you again soon!
```
