# Mark Bot User Guide

## Features 

### Add Task

- Todo
  - input: todo {taskName}
- Deadline
  - input: deadline {taskName} /by {due date}
- Event
  - input: event {taskName} /from {from date} /to {to date}

Note: dates can be inputted as yyyy-MM-dd and it will be autoformatted when printing the list

### Print List
- Print entire list
  - input: list
- Filter list by keyword
  - input: find {keyword}
- Filter list by date
  - input: due by {keyword}

### Mark Tasks
- Mark task
  - input: mark {index}
  - marks the task #index as complete
- Unmark task
  - input: unmark {index} 
  - unmarks the task #index as uncomplete

### Delete Tasks
- Delete task
  - delete {index}
  - deletes the task #index