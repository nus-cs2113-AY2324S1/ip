# Lexi Task Manager - User Guide
Lexi is a desktop app for managing different tasks, including todos, events and deadlines. Lexi is taking advantage of the Command Line Interface and is optimized for that to bring your task management and productivity to a next level. 

## Features 
**Notes about the command format:**
+ Words in _UPPER_CASE_ are the parameters supplied by the user.
  E.g., in _todo DESCRIPTION_, _DESCRIPTION_ is a parameter to be replaced with actual data, like _todo read book_.
+ Items in square brackets are optional.
  E.g., _find [KEYWORD]_ can be used as _find_ or as _find book_
+ Parameters must be provided in the correct order.
  E.g., if the command specifies _deadline DESCRIPTION DUE_DATE_, _deadline DUE_DATE DESCRIPTION_ is not acceptable.
+ The commands are not case-sensitive.
  E.g., _find_ as well as _FiNd_ is acceptable input.
  
### Listing all tasks: _list_
Shows a list of all tasks in the task manager.

Format: _list_

### Adding a todo: _todo_
Adds a todo to the task list.

Format: _todo DESCRIPTION_

Examples:
+ _todo read book_
+ _todo join sports club_

### Adding a deadline: _deadline_
Adds a task with a specific deadline to the task list. The provided date and time should be in the format 'yyyy-mm-dd hh:mm'

Format: _deadline DESCRIPTION /by DATETIME

Examples:
+ _deadline apply to Harvard /by 2023-12-06 23:59_
+ _deadline organize present for birthday party /by 2023-08-08 14:00_
+ _deadline Submit assignment /by 2023-10-06 23:59_

### Adding an event: _event_
Adds an event that starts and ends at specific times.

The provided dates and times should be in the format 'yyyy-mm-dd hh:mm'

Format: _event DESCRIPTION /from START_DATETIME /to END_DATETIME

Examples:
+ _event project meeting /from 2022-08-06 13:00 /to 2022-08-06 14:00_
+ _event Welcome Party at NUS /from 2023-08-14 17:00 /to 2023-08-14 21:00_
+ _event Coding Workshop /from 2023-10-06 19:00 /to 2023-10-06 21:00_

### Marking a task as done: _mark_
Indicates that a task is completed with a checked box: [x].

Format: _mark INDEX_
+ Marks the task at the specified _INDEX_. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …​

Examples:
+ _mark 1_ (Marks the 1st task as done.)
+ _mark 3_ (Marks the 3rd task as done.)

### Unmarking a task: _unmark_
Reverts the checked label of a task to unmark it: [ ].

Format: _unmark INDEX_
+ Marks the task at the specified _INDEX_.
+ The index refers to the index number shown in the displayed task list.
+ The index must be a positive integer 1, 2, 3, …​

Examples:
+ _unmark 1_ (Unmarks the 1st task.)
+ _unmark 3_ (Unmarks the 3rd task.)

### Deleting a task: _delete_
Removes the specified task from the list.

Format: _delete TASK_INDEX_
+ The task at the specified _INDEX_ will be removed.
+ The index refers to the index number shown in the displayed task list.
+ The index must be a positive integer 1, 2, 3, …​

Examples:
+ _delete 1_ (Deletes the 1st task.)
+ _delete 3_ (Deletes the 3rd task.)

### Searching by keyword: _find_
Finds and displays all tasks that contain the given keyword in their description.

Format: _find [KEYWORD]_
+ The search is case-insensitive. E.g., _book_ will match _Book_.
+ You can list all tasks by leaving the keyword blank.

Examples:
+ _find book_ returns _read book_ and _read Book_
+ _find oo_ returns _read book_ and _read Book_
+ _find_ returns all tasks

### Terminate and save task manager: _bye_
Saves data and exits Lexi.

Format: _bye_

### Saving the data
Lexi automatically saves your tasks in the hard-disk after each user input. 
There is no need to save the data manually.

### Editing the data file
Lexi stores the data as a txt file _data/duke.txt_. 
Advanced user can update data directly by editing that file.

**Warning:** Make sure to use the correct data formatting, which is different to the one you will encounter in the tool interaction. Lexi will remove of all corrupted data lines on execution of the program.
