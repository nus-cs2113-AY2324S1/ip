# User Guide for Oriento

## Features 

### Notes about the input command format

- For each type command, a specific `COMMAND FORMAT` is required. Please go to the following to look for the details of each command.
  e.g. "todo description" means to start with keyword todo and follows by the content


- Both `CAPITAL LETTER` and `small letter` are acceptable. But consecutive white space will be eliminated automatically.

- Tasks such as dealine and event task would required time limit. Please use the following `time format`:
  1. Date-Time format: `DD/MM/YYYY HHMM`. e.g. 21/10/2023 1730
  2. if the 'time is not specified', please use 2359 by default.
  Note that apart from the above formats, the current version of app will treat any time input as valid time.
  e.g. Friday, Birthday of Ethan, etc. Please make wise use of the time input.

### Feature-XYZ

Description of the feature.

## Command Type

### `Todo` - Adding a todo task

A todo task is a general task that doesn't need any time constraint.

Format: `todo Descrption`
Example: todo read book

Expected outcome:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

### `Deadline` - Adding a deadline task

A deadline task typically refers to task with a due time.

Format: `deadline Description /by Date Time`
Example: deadline read book /by 20/10/2022 1200

Expected outcome:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

### `Event` - Adding a event task

A event task refers to task over a specific period

Format: `event Description /from Date-Time /to Date-Time`

Example: event read book /from 20/10/2022 1200 /to 27/10/2022 2359

Expected outcome:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```
