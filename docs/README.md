# Magpie User Guide

Magpie is a chatbot for **managing your tasks** via a Command Line Interface (CLI). Using intuitive commands, Magpie can help you keep track of your **Todos, Deadlines, and Events** with features such as **add, mark, view,** and more! 

## Table of Content

* [Quick start](#quick-start)
* [Command Summary](#feature-and-command-summary)
* [Features](#features)
  * [List: `list`](#-list---list-all-current-tasks)
  * [Adding a Todo task: `todo`](#-todo---add-todo-task)
  * [Adding a Deadline task: `deadline`](#-deadline---add-deadline-task)
  * [Adding an Event task: `event`](#-event---add-event-task)
  * [Marking a Task:  `mark`](#-mark---mark-a-task)
  * [Unmarking a Task: `unmark`](#-unmark---unmark-a-task)
  * [Deleting a Task: `delete`](#-delete---delete-a-task)
  * [Finding a Task: `find`](#-find---find-tasks)
  * [Exiting the program: `bye`](#-bye---exits-magpie)
  * [Saving Tasks in hard disk](#-saving-tasks-in-hard-disk)

## Quick Start

1. Install `Java 11` from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) if you do not have it installed.
2. In your command terminal, navigate to the directory containing `Magpie.jar`.
3. Execute command `java -jar Magpie.jar`

## Feature and Command Summary

| Command  | Description                                                                      | Usage                                  |
|----------|----------------------------------------------------------------------------------|----------------------------------------|
| list     | View all your tasks in one go                                                    | `list`                                 |
| todo     | Adds a Todo task                                                                 | `todo DESCRIPTION`                     |
| deadline | Adds a Deadline task                                                             | `deadline DESCRIPTION /by BY`          |
| event    | Adds an Event task                                                               | `event DESECRIPTION /from FROM /to TO` |
| mark     | Mark your task as completed to cross out your list                               | `mark INDEX`                           |
| unmark   | Unmark your task as uncompleted if you marked it by mistake or forgot something. | `unmark INDEX`                         |
| delete   | Delete a task if you're finished with a task and don't want to keep track of it  | `delete INDEX`                         |
| find     | Looking for a specific task? Find it quickly with a **keyword**                  | `find KEYWORD`                         |
| bye      | Exits Magpie                                                                     | `bye`                                  |


## Features

> **IMPORTANT** ❗
> 
> Words in **UPPER_CASE** are compulsory parameters to be supplied by the User.

### ⭐ `list` - List all current tasks

Prints a list of current tasks and their details.

**Example of usage:** 

`list`

**Expected outcome:**

Tasks printed in the order they were added.

```
____________________________________________________________
Here are the tasks in your list: 
1. [T][ ] return book
2. [D][ ] do math homework (by: 2pm)
3. [E][ ] science exam (from: 2pm to: 5pm)
____________________________________________________________
```

### ⭐ `todo` - Add Todo task

Adds a Todo task to the list.

**Format:**
`todo DESCRIPTION`

**Example of usage:** 

`todo return book`

**Expected outcome:**

Success message showing added Todo task and number of tasks in list.

```
____________________________________________________________

Got it. I've added this task: 
  [T][ ]  return book
Now you have 1 task(s) in the list.
____________________________________________________________
```
### ⭐ `deadline` - Add Deadline task

Adds a Deadline task to the list with a specified deadline.

**Format:**
`deadline DESCRIPTION /by BY`

**Example of usage:** 

`deadline math homework /by Friday`

**Expected outcome:**

Success message showing added Deadline task and number of tasks in list.

```
____________________________________________________________

Got it. I've added this task: 
  [D][ ]  math homework  (by: Friday)
Now you have 2 task(s) in the list.
____________________________________________________________
```

### ⭐ `event` - Add Event task

Adds an Event task to the list with a specified starting and ending time.

**Format:**

`event DESECRIPTION /from FROM /to TO`

**Example of usage:** 

`event science exam /from 2pm /to 5pm`

**Expected outcome:**

Success message showing added Event task and number of tasks in list.

```
____________________________________________________________

Got it. I've added this task: 
  [E][ ]  science exam  (from: 2pm to: 5pm)
Now you have 3 task(s) in the list.
____________________________________________________________
```
### ⭐ `mark` - Mark a Task

Marks a Task specified by given index as done.

**Format:** 

`mark INDEX`

* **INDEX** must be a positive integer number
* You can find a Task **INDEX** via the number shown in `list`.

**Example of usage:** 

`mark 1`

**Expected outcome:**

Success message showing task has been marked as done with [X]

```
____________________________________________________________

Nice! I've marked this task as done:
  [T][X] return book
____________________________________________________________
```
### ⭐ `unmark` - Unmark a Task

Unmark a Task specified by given index as not done.

**Format:** 

`unmark INDEX`

* **INDEX** must be a positive integer number
* You can find a Task **INDEX** via the number shown in `list`.
  
**Example of usage:** 

`unmark 1`

**Expected outcome:**

Success message showing task has been unmarked.

```
____________________________________________________________

OK, I've marked this task as not done yet:
  [T][ ] return book
____________________________________________________________
```

### ⭐ `delete` - Delete a Task

Delete a Task specified by given index from the list.

**Format:** 

`delete INDEX`

* **INDEX** must be a positive integer number
* You can find a Task **INDEX** via the number shown in `list`.
  
**Example of usage:** 

`delete 1`

**Expected outcome:**

Success message showing task has been deleted and number of remaining tasks.

```
____________________________________________________________

Noted. I've removed this task: 
  [T][ ] return book
Now you have 2 task(s) in the list.
____________________________________________________________
```

### ⭐ `find` - Find Tasks

Finds all task containing specified keyword and displays their details.

**Format:**

`find KEYWORD`

**Example of usage:** 

`find exam`

**Expected outcome:**

Displays details of found tasks, if any.

```
____________________________________________________________

Finding tasks with keyword: exam.....

1. [E][ ] science exam (from: 2pm to: 5pm)
1 task(s) were found with keyword: exam

____________________________________________________________
```

### ⭐ `bye` - Exits Magpie

Exits Magpie with a farewell message.

**Format:**

`bye`

**Expected outcome:**

Displays farewell message and exits program.

```
____________________________________________________________
Bye. Hope to see you again soon!

____________________________________________________________
```
### ⭐ Saving Tasks in hard disk

Magpie saves all your tasks automatically! 

You may view your saved tasks in `data.txt`, found in your `data` folder located in the same directory as `Magpie.jar`. 

Full file path: `data/data.txt` 

