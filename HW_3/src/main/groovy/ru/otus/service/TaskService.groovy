package ru.otus.service

import ru.otus.model.Task

import java.time.LocalDateTime

interface TaskService {

    Task create(Task task)

    Task get(LocalDateTime time)

    List<Task> getAll()

    Task update(Task task)

    delete(Task task)
}