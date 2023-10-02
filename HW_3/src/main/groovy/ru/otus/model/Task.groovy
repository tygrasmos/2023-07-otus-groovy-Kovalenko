package ru.otus.model

import java.time.LocalDateTime

class Task {

    LocalDateTime dateTimeStartTask
    LocalDateTime dateTimeEndTask
    String taskName
    String taskDescription
    Long taskDuration
    List<Action> actionList
}
