# User Guide

Jerry is a desktop application designed to help you manage your tasks. It is optimized for use via Command Line Interface (CLI).
It handles various kind of tasks such as todos, deadlines and even events.

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest Jerry.jar from [here](https://github.com/martinschnder/ip/releases/tag/A-JavaDoc).

3. Copy the file to the folder you want to use as the home folder for your task list.

4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Jerry.jar command to run the application.
   A GUI similar to the below should appear in a few seconds.

## Features

### Add a Task

You can easily add a task to you list by using one of these keyword : `todo`, `deadline` or `event` followed by the description and the time parameters.
Deadlines take one argument prefixed by the `/by` keyword.
Events take two argument `/from` and `/to`.
e.g `event CS2113 lecture /from tuesday 9am /to tuesday 11am` can be used to create an event reminder.

### List the tasks

The `list` command is used to display all your current tasks.

### Mark a task as done

Once you have listed your current tasks, each one is assigned an index. You can mark a specific task as done with the `mark` command followed by the indicated index.
e.g `mark 2`

### Mark a task as not done

The same way, you can unmark a task using the `unmark` keyword.
e.g `unmark 2`

### Remove a task from your list

Using the same index mechanism, you can also remove a task from your list using the `delete` keyword.
e.g `delete 2`

### Find a specific task from a list of keywords

To easily access your tasks, you can use the `find` command followed by several keywords. Any tasks containing one of the keywords will be then displayed.
You can then use directly the indexes provided to mark a task or delete it.
e.g `find lecture java`
