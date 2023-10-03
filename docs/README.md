# User Guide

##### _*Chatty*_ is a command-line application that keeps track of your tasks so that you would not need to worry about forgetting them.


- [Setting up](#setting-up)
- [Features](#features)
    - [Add a Todo task](#create-a-todo-task)
    - [Add a Deadline task](#create-a-deadline-task)
    - [Add an Event task](#create-an-event-task)
    - [List all tasks](#list-all-tasks)
    - [Mark a task as completed](#mark-a-task-as-complete)
    - [Mark a task as not completed](#unmark-a-task)
    - [Delete a task](#delete-a-task)
    - [Find a task](#find-a-task)
    - [Exit Chatty](#exit-the-application)
- [Command Summary](#command-summary)
- [Frequently Asked Questions (FAQ)](#frequently-asked-questions)


## Setting Up
1. Ensure that your device is equipped with Java 11. Not installed? [Install Java now](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the jar file from the developer
3. Extract the jar file into your favourite file path.
4. Open up command prompt in windows or bash/terminal in macOS/Linux.
5. Change directory to where you store the 'jar' file.
6. In the terminal, run the command `java -jar ip.jar`.
7. If you have done the above steps correctly, you will see the following message in your console.
```
____________________________________________________________
Hello! I'm Chatty
What can I do for you?
____________________________________________________________
```


## Features 
Things to take note:
- `commands` are defined in **lower case** only.E.g. `todo`.
- Words in anchor tags `<>` are details to be provided by the user. E.g. `<Date>`.
- Keywords for the commands are to be **strictly followed**. It is marked by a forward slash `/` and follow by terms in **lower case**. E.g.`/from` 
- Date needs to be specified in `yyyy-MM-dd` format. E.g: `2023-12-25`


### Create a Todo Task

Having a task that you need to do? Create a Todo Task so that you won't forget it.

#### Usage

##### `todo` - creates a Todo task
##### Format: `todo <TASK DESCRIPTION>`
Example of usage:

You need to send out Christmas invitations. Record it down before you forget.
```
todo Send out Christmas Invitations
```
Expected outcome:
```
____________________________________________________________
	Got it. I've added this task:
		[T][ ] Send out Christmas Invitations
	Now you have 1 task(s) in the list.
____________________________________________________________
```
The tasks to `Send out Christmas Invitations` for Christmas is now recorded in the Task List.

The symbol `[T]` indicates that it is a task with no deadline.

The symbol `[ ]` indicates that it has not yet been completed.

### Create a Deadline Task

What if there is a deadline for a task? No worries, you can add a deadline to your task list for record.

#### Usage


##### `deadline` - creates a task with a deadline
##### Format: `deadline <TASK DESCRIPTION> /by <End Date in yyyy-MM-dd format>`
Example of usage:

There is a deadline to marinate the turkey in time for Christmas!
```
deadline Marinate the Turkey /by 2023-12-23
```
Expected outcome:
```
____________________________________________________________
	Got it. I've added this task:
		[D][ ] Marinate the Turkey (by: Dec 23 2023)
	Now you have 2 task(s) in the list.
____________________________________________________________
```
The tasks to `Marinate the Turkey` for Christmas is now recorded in the Task List with a deadline of `Dec 23 2023`.

If the above steps are followed, there will be 2 tasks in the list.

The symbol `[D]` indicates that it is a task with deadline.

The symbol `[ ]` indicates that it has not yet been completed.

### Create an Event Task

Creates a task with a start date and an end date.

#### Usage


##### `event` - creates a task with a start date and an end date
##### Format: `event <TASK DESCRIPTION> /from <START DATE> /to <END DATE>`
Example of usage:

Now you realise that there is a turkey sale within a period of time. 
But it only starts after a certain date and ends at a particular date. 
You can create an event task to keep track of it.
```
event Buy a Turkey /from 2023-12-10 /to 2023-12-22
```
Expected outcome:
```
____________________________________________________________
	Got it. I've added this task:
		[E][ ] Buy a Turkey (from:Dec 10 2023 to:Dec 22 2023)
	Now you have 3 task(s) in the list.
____________________________________________________________
```
The tasks to `Buy a Turkey` for Christmas is now recorded in the Task List.

If the above steps are followed, there will be 3 tasks in the list.

The symbol `[E]` indicates that it is a task to be completed in a specific timeframe.

The symbol `[ ]` indicates that it has not yet been completed.


### List all tasks

List down all the tasks that are created.

#### Usage


##### `list` - List all the tasks in the task list.
##### Format: `list`
Example of usage:

Created a few tasks? Now it's time to view all of them.
```
list
```
Expected outcome:
```
____________________________________________________________
1. 	[T][ ] Send out Christmas Invitations
2. 	[D][ ] Marinate the Turkey (by: Dec 23 2023)
3. 	[E][ ] Buy a Turkey (from:Dec 10 2023 to:Dec 22 2023)
____________________________________________________________

```
All the tasks that are recorded by the task list is displayed.

If the above steps are followed, these are the tasks that will be displayed.

### Mark a task as complete

Complete a task? You can mark it as complete.

#### Usage


##### `mark` - Mark a task as complete.
##### Format: `mark <TASK ID>`
Example of usage:

You have successfully sent out invitations for the Christmas Reunion. Time to mark the task as complete.
The id of a task is indicated with the command `list`.
```
mark 1
```
Expected outcome:
```
____________________________________________________________
1. 	[T][x] Send out Christmas Invitations
2. 	[D][ ] Marinate the Turkey (by: Dec 23 2023)
3. 	[E][ ] Buy a Turkey (from:Dec 10 2023 to:Dec 22 2023)
____________________________________________________________
```
It can be observed that the task `Send out Christmas Invitations` has the symbol`[x]`. 
This indicates that the task is marked as completed. 

### Unmark a task

Accidentally mark a task as completed? You can mark it as not completed with the `unmark` command.

#### Usage


##### `unmark` - creates a Todo task
##### Format: `unmark <TASK ID>`
Example of usage:

Realised that you have forgotten to invite one of your dear friends after marking it as done? Unmark it!
```
unmark 1
```
Expected outcome:
```
____________________________________________________________
1. 	[T][ ] Send out Christmas Invitations
2. 	[D][ ] Marinate the Turkey (by: Dec 23 2023)
3. 	[E][ ] Buy a Turkey (from:Dec 10 2023 to:Dec 22 2023)
____________________________________________________________
```
It can be observed that the task `Send out Christmas Invitations` has the symbol`[ ]`. 
This indicates that the task is not completed.

### Delete a task

If you have entered a task erroneously, or you do not want it to be in the task list anymore, you can delete the task.

#### Usage

##### `delete` - deletes a task
##### Format: `delete <TASK ID>`
Example of usage:

After sending out all the Christmas invitations, you do not want the task to be in the task list anymore since 
it is getting long.
```
delete 1
```
Expected outcome:
```
____________________________________________________________
	Noted. I've removed this task:
	[T][ ] Send out Christmas Invitations
	Now you have 2 tasks in the list.
____________________________________________________________
```
The tasks to `Send out Christmas Invitations` for Christmas is deleted. 
It shows you the task that is removed from the task list.


### Find a task

Remember some words in the tasks and not sure if it is in the task list? Use the `find` command to locate your task.

Note: The case of the search term must be identical to the one in the task description
#### Usage


##### `find` - find a task that contains a certain keyword.
##### Format: `find <SEARCH WORD>`
Example of usage:

You would like to check the deadline to marinate the turkey. Hence, instead of looking through all the tasks, 
you can use the `find` command to locate task(s) with the word 'Marinate'.
```
find Marinate
```
Expected outcome:
```
____________________________________________________________
Here are your results:

	[D][ ] Marinate the Turkey (by: Dec 23 2023)
____________________________________________________________
```
The task that contains the search term 'Marinate' is being displayed.
  

### Exit the application

The `bye` command  exits the application.

#### Usage

##### `bye` - find a task that contains a certain keyword.
##### Format: `bye`
Example of usage:

You are satisfied with the updating of the task list this time round, and you wish to exit the application 
for the time being.
```
bye
```
Expected outcome:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
Chatty terminates.





## Command Summary

| Command    | Description                              | Format                                                            |
|------------|------------------------------------------|-------------------------------------------------------------------|
| `todo`     | Adds a task to the task list.            | `todo <TASK DESCRIPTION>`                                         |
| `deadline` | Adds a task with end date .              | `deadline <TASK DESCRIPTION> /by <End Date in yyyy-MM-dd format>` |
| `event`    | Adds a task with start date and end date | `event <TASK DESCRIPTION> /from <START DATE> /to <END DATE>`      |
| `list`     | Lists all the task in the task list      | `list`                                                            |
| `mark`     | Mark a task as done.                     | `mark <TASK ID>`                                                  |
| `unmark`   | Mark a task as not done.                 | `unmark <TASK ID>`                                                |
| `unmark`   | Mark a task as not done.                 | `unmark <TASK ID>`                                                |
| `delete`   | Delete a task.                           | `delete <TASK ID>`                                                |
| `find`     | Find a task containing the search term.  | `find <SEARCH WORD>`                                              |
| `bye`      | Exits Chatty                             | `bye`                                                             |

## Frequently Asked Questions
_**Q**: How is the task saved?_

**A**: The file is saved in a 'duke.txt' file located in a 'data' directory in the path where the command is run. 
It is advised to run this app on your root path when you open your terminal or command prompt so that you have access 
to the same file everytime Chatty runs.

_**Q**: What are some things that I need to take note of?_

**A**: Firstly, please do not edit the contents of the file. 
If any of the tasks is of the wrong format, the entire task will be deleted upon loading.
Next, please do not terminate the program unexpectedly, otherwise, all progress will be deleted.
Lastly, please do not open the 'duke.txt' file upon starting up. Otherwise, all contents will be erased.

[//]: # (Example of usage:)

[//]: # ()
[//]: # (`keyword &#40;optional arguments&#41;`)

[//]: # ()
[//]: # (Expected outcome:)

[//]: # ()
[//]: # (Description of the outcome.)

[//]: # (### Feature-close the app)

[//]: # ()
[//]: # (Description of the feature.)

[//]: # ()
[//]: # (## Usage)

[//]: # ()
[//]: # (### `Keyword` - Describe action)

[//]: # ()
[//]: # (Describe the action and its outcome.)

[//]: # ()
[//]: # (Example of usage: )

[//]: # ()
[//]: # (`keyword &#40;optional arguments&#41;`)

[//]: # ()
[//]: # (Expected outcome:)

[//]: # ()
[//]: # (Description of the outcome.)

[//]: # ()
[//]: # (```)

[//]: # (expected output)

[//]: # (```)
