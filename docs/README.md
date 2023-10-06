# User Guide of Elvin

This is Elvin, the best personal task management system you will ever use!

## Quick Start

1. Ensure that you have `Java 11` installed on your laptop or computer.
2. Download the latest version of the JAR file.
3. Move the file to a folder that you want to use for the application
4. Open up CLI and traverse until you reach your app folder.
5. Type in `java -jar "Elvin.jar"`.
6. The app will create a data folder and text file and it will close again.

## Features

### Add Todo : `todo`

Add a todo task to the the list of tasks.

**Format:** `todo DESCRIPTION`

Example:

```
todo borrow book
```


### Add Deadline : `deadline`

Add a deadline task to the list of task with specific deadline.

**Format:** `deadline DESCRIPTION /by DEADLINE`


Example:

```
deadline homework /by Sunday
```


### Add Event : `event`

Add a event task to the list of task with a time period marked by from and to.

**Format:** `event DESCRIPTION /from START /to END`

Example:

```
event midterm exam /from 15:00 /to 17:00
```


### Delete Task : `delete`

Delete task from list of task based on index.

**Format:** `delete INDEX`

Example:

```
delete 2
```


### List Task : `list`

List all tasks in the list of tasks.

**Format:** `list`

Example:

```
list
```


### Mark Task : `mark`

Mark a task as done based on index.

**Format:** `mark INDEX`

Example:

```
mark 2
```


### Unmark Task : `unmark`

Unmark a task as done based on index.

**Format:** `unmark INDEX`

Example:

```
unmark 5
```


### Find Task : `find`

List all tasks that contains the given string

**Format:** `find DESCRIPTION`

Example:

```
find book
```

### EXIT : `bye`

Exit the app

**Format:** `bye`


Example:

```
bye
```

### Save Data
The data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Edit Data
The data is edited in the hard disk automatically after any command that changes the data.
If you want to edit the data manually, you can use the app to edit it.

## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Elvin folder with same relative location.
