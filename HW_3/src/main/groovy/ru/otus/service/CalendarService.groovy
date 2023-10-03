package ru.otus.service

import ru.otus.model.Action
import ru.otus.model.Task

import java.time.LocalDateTime

interface CalendarService <T> {

   Boolean validate(T entity, List<T> entityList)

   Task createNewTask(Task task)

   Action createNewAction(Action action)

   def deleteTask(Task task)

   def deleteAction(Action action)

   def renameTask(Task task)

   def renameAction(Action action)

   Integer getCountTaskFromDate(LocalDateTime dateTime)

   String getBusyTimeFromDate(LocalDateTime dateTime)

   List<Task> getAllTaskFromDate(LocalDateTime dateTime)

   def fireEvent(Task task, LocalDateTime startTime)

}