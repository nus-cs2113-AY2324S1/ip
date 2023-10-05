# User Guide

MudMud is a chatbot application for creating and managing tasks with the use of a Command Line Interface (CLI). Tasks, like todo, deadline, and event, are supported in this app. This app will store its data via a textfile in a seperate folder.

## How to Setup

1. Ensure that you have `Java 11` installed on your laptop or computer.
2. Download the latest version of the JAR file.
3. Move the file to a folder that you want to use for the application
4. Open up CLI and traverse until you reach your app folder.
5. Type in `java -jar "MudMud.jar"`.
6. The app will create a data folder and text file and it will close again.
7. Run the app again and the app is ready to use!
   
## Features 
* Veiwing your current tasks - `list`
* Adding a todo task - `todo`
* Adding a deadline task - `deadline`
* Adding an event task - `event`
* Marking a task - `mark`
* Unmarking a task - `unmark`
* Deleting a task - `delete`
* Searching for tasks - `find`
* Exiting the application - `bye`

## Veiwing your current tasks - `list`
List all tasks available in the app.

## Adding a todo task - `todo`
Add a new todo task into the list with the format below:
`todo 'task'`

**Example:**
`todo buy book`

## Adding a deadline task - `deadline`
Add a new deadline task into the list with the format below:
`deadline 'task' /by 'specified deadline'`

Your `specified deadline` should be in this format:
`yyyy-MM-dd HHmm`

**Example:**
`deadline submit homework /by 2022-10-23 2359`

## Adding an event task - `event`
Add a new event task into the list with the format below:
`event 'task' /from 'specified start date' /to 'specified end date'`

Your `specified start date` and `specified end date` should be in this format:
`yyyy-MM-dd HHmm`

**Example:**
`event party /from 2022-07-10 1900 /to 2022-07-10 2100`

## Marking a task - `mark`
Mark a task in the list with the format below:
`mark 'task number'`

**Example:**
`mark 2` will mark the second task in the list.

## Unmarking a task - `unmark`
Unmark a task in the list with the format below:
`unmark 'task number'`

**Example:**
`unmark 3` will unmark the third task in the list.

## Deleting a task - `delete`
Delete a task in the list with the format below:
`delete 'task number'`

**Example:**
`delete 1` will delete the first task in the list.

## Searching for tasks - `find`
Find tasks with the matching keyword in the list with the format below.
`find 'keyword'`

**Example:**
`find book` will find tasks with the keyword `book` in the list.

## Exiting the application - `bye`
Exit the application.
