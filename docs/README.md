# Python User Guide

**Python** task manager will be a blessing for users with fast typing speed. You can
add three types of tasks, mark tasks as completed, delete tasks, and if necessary
find tasks using a keyword!

## Features 
Python comes with the following core features:
* [Add Todo](#add-todo)
* [Add Deadline](#add-deadline)
* [Add Event](#add-event)
* [Mark Task](#mark-task)
* [Unmark Task](#unmark-task)
* [Delete Task](#delete-task)
* [Find Tasks](#find-tasks)
* [List Tasks](#list-tasks)
* [Exit](#exit-program)

### Add Todo
Format:
`todo [todo description]`

Example of usage:
`todo buy groceries`

Add a simple todo!

### Add Deadline

Format:
`deadline [deadline description] /by [due date]`

Example of usage:
`deadline lab 2 /by friday night`

Add a deadline with due date!


### Add Event

Format:
`event [event description] /from [start date] /to [end date]`

Example of usage:
`event math exam /from monday 2pm /to monday 4pm`

Add an event to keep track of both start and end times of an activity!

### Mark Task

Format:
`mark [index of the task - 1 based]`

Example of usage:
`mark 1`

Mark the task at index as done!

### Unmark Task

Format:
`unmark [index of the task - 1 based]`

Example of usage:
`unmark 1`

Unmark the task at index as not done yet!

### Delete Task

Format:
`delete [index of the task - 1 based]`

Example of usage:
`delete 1`

Delete the task at the index!

### Find Tasks

Format:
`find [keyword]`

Example of usage:
`find exam`

List the tasks that contains the keyword in the description!

### List Tasks

Format:
`list`

Example of usage:
`list`

List all the tasks along with completion status and task type!

### Exit Program

Format:
`bye`

Example of usage:
`bye`

Greet Python 'bye' to exit the program!

