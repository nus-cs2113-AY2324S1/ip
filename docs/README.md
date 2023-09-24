# Remy User Guide

## Features 

Remy is a task tracker that will help you with managing your tasks as well as being able to mark them as complete/incomplete.<br />

## Usage

### `List` - Lists all tasks

This command lists out all your current tasks for you to view.

Command: `list`

### `Tasks` - Create a task

There are 3 different types of tasks, each with their own unique way of creation

Command: `todo [TASK_DESCRIPTION]`
This command creates a "todo" task with everything following the word `todo` being the description of the task

Example: `todo Wash Laundry`
Creates a Todo task with the description `Wash Laundry`.

Command: `deadline [TASK_DESCRIPTION] /by [DEADLINE_DATE]`
This command creates a "deadline" task with everything between the words `deadline` and `/by` consisting of the task description
and everything following the word `/by` being the deadline date of the task.

Example: `deadline Finish Assignment 1 /by January 7`
Creates a Deadline task with the description `Finish Assignment 1` with a due date of `January 7`.

Command `event [TASK_DESCRIPTION] /from [DATE_FROM] /to [DATE_TO]`
This command creates an "event" task with everything between the words `event` and `/from` consisting of the task description,
everything between the words `/from` and `/to` consisting of the starting date of the event, and everything following the word
`/to` consisting of the end date of the event.

Example: `event Music Concert /from August 16 /to August 18`
Creates an Event task with the description `Music Concert` from `August 16` to `August 18`.

### `Marking Tasks` - Mark tasks as done/undone
All tasks can either be marked as done, or marked as undone.

Command: `mark [TASK_NUMBER]`
Marks the nth task in the list as done.

Command: `unmark [TASK_NUMBER]`
Marks the nth task in the list as not done.

### `Deleting Tasks` - Delete tasks from the list

Command: `delete [N]`
Deletes the Nth task in the list.

Example: `delete 2`
This command will delete the 2nd task in the list.

### `Finding Tasks` - Search for specific tasks

Command: `find [KEYWORD]`
Finds all Tasks in the list whose description contains the specified KEYWORD and returns them all to the user.

Example: `find walk`
This command will return all tasks whose description contains the word `walk`.

### `Exit` - Exit the program

Command: `bye`
This command tells Remy goodbye and exits the application.

### All other commands
Remy will let the user know an invalid command is input by explaining that the user's input was not understood.
