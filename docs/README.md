# User Guide of ZenBot

This is ZenBot, the best personal task management system you will ever use!

## Features 

### Add Todo - `Todo`

Add a todo task to the tasklist.

**Format:** `todo DESCRIPTION`

**Data Type:**\
DESCRIPTION: String

Example:

```
todo buy groceries
todo book movie ticket
```


### Add Deadline

Add a deadline task to the tasklist, with a date to complete by.

**Format:** `deadline DESCRIPTION /by DEADLINE`

**Data Type:**\
DESCRIPTION: String
DEADLINE: LocalDate

Example:

```
deadline homework /by 2019-01-01
```


### Add Event

Add a event task to the tasklist, with a time period from and to.

**Format:** `event DESCRIPTION /from STARTTIME /to ENDTIME`

**Data Type:**\
DESCRIPTION: String
STARTTIME: String
ENDTIME: String

Example:

```
event concert /from 2000-01-01 /to 2000-01-02
```


### Delete Task

Delete task from tasklist base on index.

**Format:** `delete INDEX`

**Data Type:**\
INDEX: integer

Example:

```
delete 5
```


### List Task

List all tasks in the tasklist.

**Format:** `list`

**Data Type:**\
-

Example:

```
list
```


### Mark Task

Mark a task as complete base on index.

**Format:** `mark INDEX`

**Data Type:**\
INDEX: integer

Example:

```
mark 5
```


### Unmark Task

Unmark a task base on index.

**Format:** `unmark INDEX`

**Data Type:**\
INDEX: integer

Example:

```
unmark 5
```


### Find Task

List all tasks that contains the given string

**Format:** `find SEARCH`

**Data Type:**\
SEARCH: String

Example:

```
find buy
```

### Farewell

Quit the app

**Format:** `bye`

**Data Type:**\
-

Example:

```
bye
```
