# User Guide of Cara
Cara: a fun and friendly task management bot that helps you to organise your task efficiently and easily :)

### Task Management  
- **Add Tasks**: Easily add different task types to your to-do list, such as Todo, Deadline, and Event.
- **Mark Tasks as Done**: Mark tasks as completed when you've finished them.
- **Delete Tasks**: Remove tasks that you no longer need.
- **List Tasks**: View a list of all your tasks.
- **Find Tasks**: Search for specific tasks using keywords.

## Features

### 1. Add Todo Task - `todo` 

Add a todo task to the tasklist

Format: `todo DESCRIPTION`

Example of usage: 

```
todo buy book
todo return money 
```

### 2. Add Deadline Task - `deadline` 

Add a deadline task to the tasklist, with a date to complete by

Format: `deadline DESCRIPTION /by DEADLINE`

Example of usage: 

```
Deadline do homework /by 8 nov 2023
```

### 3. Add Event Task - `event` 

Add an event task to the tasklist, with a start and end time

Format: `event DESCRIPTION /from STARTTIME /to ENDTIME`

Example of usage: 

```
event concert /from 6pm /to 9pm
```

### 4. Delete Task - `delete` 

Delete a task from the tasklist base on index

Format: `delete INDEX`

- The index refers to the index number shown in the tasklist
- the index **must be a positive integer** 1,2,3...

Example of usage: 

```
delete 5
```

### 5. List Task - `list` 

Shows a list of all tasks in the list

Format: `list`

Example of usage: 

```
list
```

### 6. Find Task - `find` 

Find tasks that contains any of the keywords

Format: `find KEYWORD`

- The search is case-insensitive, e.g `Book` will match `book`

Example of usage: 

```
find book
```

### 7. Mark Task - `mark` 

Mark a task as done based on their index

Format: `mark INDEX`

Example of usage: 

```
mark 1
```

### 8. Unmark Task - `unmark` 

Mark a task as not done based on their index

Format: `unmark INDEX`

Example of usage: 

```
unmark 1
```

### 9. Ending the program - `bye` 

Exits the program

Format: `bye`

Example of usage: 

```
bye
```

