# Simon User Guide

Simon is a CLI application that helps the user to manage their tasks.

## Features 

```
Notes about the command format:

Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in 'todo DESCRIPTION', DESCRIPTION is a parameter which can be used as 'todo read book'.
```

### Add todo task: `todo`

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

### Add deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by BY`

### Add event task: `event`

Adds an event task to the task list.

Format: `event DESCRIPTION /from FROM /to TO`

### View list: `list`

View the current list of tasks.

Format: `list`

### Mark as Done: `mark`

Marks a chosen task as done

Format: `mark TASKNUMBER`

### Unmark as Done: `unmark`

Unmarks a chosen task as done

Format: `unmark TASKNUMBER`

### Delete task: `delete`

Deletes a chosen task

Format: `delete TASKNUMBER`

### Find matching task: `find`

Find tasks that contain a specific keyword

Format: `find KEYWORD`

### Close application: `bye`

Closes the application

Format: `bye`

### Saving the data

Simon's data is saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

Simon's data is saved automatically as a txt file `[JAR file location]/data/simon.txt`.  Advanced users are welcome to 
update data directly by editing that data file.