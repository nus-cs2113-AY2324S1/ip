# User Guide

## Features 

### Feature: Add Todo, Deadline or Event

Use app to record todos, deadlines and events with a custom user description for each added entry. Deadlines support due dates and events support start and end dates. Todos, deadlines and events can be removed as well. 

Can also search for tasks with keywords from their task description.

### Feature: Mark/unmark

Use app to mark/unmark todos/deadlines/events as done or not done yet.

### Feature: Find

Use app to find todos/deadlines/events by searching the task list for the relavent keyword in their descriptions

### Feature: List

List all todos, deadlines and events.

### Feature: Save

Todos, deadlines and events are saved in between running the program.


## Usage

### `todo`/`deadline`/`event` - Add todo, deadline or event

How to add a Todo:
todo [description]

How to add a Deadline:
deadline [description] /by [due date]

How to add an Event:
event [description] /from [start date] /to [end date]

Example of usage: 

`deadline Homework /by Friday`

Expected outcome:

Adds a new deadline with description "go to school" with due date of "Friday"

```
Created new Deadline:
 Homework  (by: Friday)
```

### `mark`/`unmark` - Mark/unmark todo, deadline or event

Mark/unmark a todo/event/deadline as done or not done yet using the mark/unmark keyword:
mark [item position in list]
unmark [item position in list]

Example of usage:

`mark 1`

Expected outcome:

Marks todo/deadline/event in position 1 of list as done

```
Marked as done:
 Homework 
```

### `list` - Lists all todos, deadlines and events

Example of usage:

`list`

Expected outcome:

```
[D][X]  Homework  (by:  Friday)
```

### `find` - Lists all todos, deadlines and events that contain search term in the description

find [search term]

Example of usage:

`find Homework`

Expected outcome:

```
[D][X]  Homework  (by:  Friday)
```

### `bye` - Exits program

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
