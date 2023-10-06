# MatinBot User Guide

Welcome to MatinBot! MatinBot is designed to help you manage tasks efficiently. Below are the instructions on how to use its features and some important guidelines.

## Features

MatinBot offers several features to help you manage tasks effectively:

### Todo

**Description:** Todo tasks are simple to-dos.

#### Usage

1. To add a Todo task, use the following command: todo <description>
Example: todo Study for CS2113

2. To list all tasks, type: list

3. To mark a task as done, use: mark <task_number>
Example: mark 1

4. To delete a task, use: delete <task_number>
Example: delete 1

### Event

**Description:** Event tasks have a date range.

#### Usage

1. To add an Event task, use: event <description> /from <start_date> /to <end_date>
Example: event CS2113 /from 4pm /to 6pm

2. To list all tasks, type: list

3. To mark a task as done, use: mark <task_number>

4. To delete a task, use: delete <task_number>

### Deadline

**Description:** Deadline tasks have a specific due date.

#### Usage

1. To add a Deadline task, use: deadline <description> /by <due_date>
Example: deadline CS2113 IP /by 06 Oct

2. To list all tasks, type: list

3. To mark a task as done, use: mark <task_number>

## Task Management Guidelines

- Ensure you follow the format for adding tasks (e.g., `/from` and `/to` for Event tasks, `/by` for Deadline tasks).
- Use the correct command format for marking and deleting tasks (`mark` and `delete`, followed by the task number).
- Always provide valid task numbers when marking or deleting tasks.
