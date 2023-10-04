# Magpie User Guide

Magpie is a chatbot for **managing your tasks** via a Command Line Interface (CLI). Using intuitive commands, Magpie can help you keep track of your **Todos, Deadlines, and Events** with features such as **add, mark, view,** and more! 

## Table of Content

* Quick start
* Command Summary
* Features
  * List: `list`
  * Adding a Todo task: `todo`
  * Adding a Deadline task: `deadline`
  * Adding an Event task: `event`
  * Marking a Task:  `mark`
  * Unmarking a Task: `unmark`
  * Deleting a Task: `delete`
  * Exiting the program: `bye`
  * Saving Tasks in hard disk

## Quick Start 

## Command Summary



## Features 

### List

View all your tasks in one go.



### Add Task [Todo/Deadline/Event]

Add a new task to keep track of. You can enter a **Todo**, **Deadline**, or **Event** task.

### Mark Task

Mark your task as completed to cross out your list!


### Unmark Task

Unmark your task as uncompleted if you marked it by mistake or forgot something.


### Delete Task

Delete a task if you're finished with a task and don't want to keep track of it.



### Find Task

Looking for a specific task? Find it quickly with a **keyword**.


## Usage

> [!IMPORTANT]
> Words in **UPPER_CASE** are compulsory parameters to be supplied by the User.

### `list` - List all current tasks

Prints a list of current tasks and their details.

Example of usage: 

`list`

Expected outcome:

Tasks printed in the order they were added.

```
____________________________________________________________
Here are the tasks in your list: 
1. [T][ ] return book
2. [D][ ] do math homework (by: 2pm)
3. [E][ ] science exam (from: 2pm to: 5pm)
____________________________________________________________
```

### `todo` - Add Todo task

Adds a Todo task to the list.

Format:
`todo DESCRIPTION`

Example of usage: 

`todo return book`

Expected outcome:

Success message showing added Todo task and number of tasks in list.

```
____________________________________________________________

Got it. I've added this task: 
  [T][ ]  return book
Now you have 1 task(s) in the list.
____________________________________________________________
```
### `deadline` - Add Deadline task

Adds a Deadline task to the list with a specified deadline.

Format:
`deadline DESCRIPTION /by BY`

Example of usage: 

`deadline math homework /by Friday`

Expected outcome:

Success message showing added Deadline task and number of tasks in list.

```
____________________________________________________________

Got it. I've added this task: 
  [D][ ]  math homework  (by: Friday)
Now you have 2 task(s) in the list.
____________________________________________________________
```

### `event` - Add Event task

Adds an Event task to the list with a specified starting and ending time.

Format:

`event DESECRIPTION /from FROM /to TO`

Example of usage: 

`event science exam /from 2pm /to 5pm`

Expected outcome:

Success message showing added Event task and number of tasks in list.

```
____________________________________________________________

Got it. I've added this task: 
  [E][ ]  science exam  (from: 2pm to: 5pm)
Now you have 3 task(s) in the list.
____________________________________________________________
```
### `mark` - Mark a Task

Marks a Task specifed by given index as done.

Format: 

`mark INDEX`

* **INDEX** must be a positive integer number
* You can find a Task **INDEX** via the number shown in `list`.

Example of usage: 

`mark 1`

Expected outcome:

Success message showing task has been marked as done with [X]

```
____________________________________________________________

Nice! I've marked this task as done:
  [T][X] return book
____________________________________________________________
```
### `unmark` - Unmark a Task

Unmark a Task specifed by given index as not done.

Format: 

`unmark INDEX`

* **INDEX** must be a positive integer number
* You can find a Task **INDEX** via the number shown in `list`.
  
Example of usage: 

`unmark 1`

Expected outcome:

Success message showing task has been unmarked.

```
____________________________________________________________

OK, I've marked this task as not done yet:
  [T][ ] return book
____________________________________________________________
```

### `delete` - Delete a Task

Delete a Task specifed by given index from the list.

Format: 

`delete INDEX`

* **INDEX** must be a positive integer number
* You can find a Task **INDEX** via the number shown in `list`.
  
Example of usage: 

`delete 1`

Expected outcome:

Success message showing task has been deleted and number of remaining tasks.

```
____________________________________________________________

Noted. I've removed this task: 
  [T][ ] return book
Now you have 2 task(s) in the list.
____________________________________________________________
```

### `find` - Find Tasks

Finds all task containing specified keyword and displays their details.

Format:

`find KEYWORD`

Example of usage: 

`find exam`

Expected outcome:

Displays details of found tasks, if any.

```
____________________________________________________________

Finding tasks with keyword: exam.....

1. [E][ ] science exam (from: 2pm to: 5pm)
1 task(s) were found with keyword: exam

____________________________________________________________
```

### `bye` - Exits Magpie

Exits Magpie with a farewell meessage.

Format:

`bye`

Expected outcome:

Displays farewell message and exits program.

```
____________________________________________________________
Bye. Hope to see you again soon!

____________________________________________________________
```
