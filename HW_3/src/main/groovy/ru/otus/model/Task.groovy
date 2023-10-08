package ru.otus.model

import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class Task {

    private LocalDateTime dateTimeStartTask
    private LocalDateTime dateTimeEndTask
    private String taskName
    private String taskDescription
    private Long taskDuration
    private List<Action> actionList

    LocalDateTime getDateTimeStartTask() {
        return dateTimeStartTask
    }

    void setDateTimeStartTask(LocalDateTime dateTimeStartTask) {
        this.dateTimeStartTask = dateTimeStartTask
    }

    LocalDateTime getDateTimeEndTask() {
        return dateTimeEndTask
    }

    void setDateTimeEndTask(LocalDateTime dateTimeEndTask) {
        this.dateTimeEndTask = dateTimeEndTask
    }

    String getTaskName() {
        return taskName
    }

    void setTaskName(String taskName) {
        this.taskName = taskName
    }

    String getTaskDescription() {
        return taskDescription
    }

    void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription
    }

    Long getTaskDuration() {
        return taskDuration
    }

    void setTaskDuration(Long taskDuration) {
        this.taskDuration = taskDuration
    }

    List<Action> getActionList() {
        return actionList
    }

    void setActionList(List<Action> actionList){
        this.actionList = new ArrayList<>(actionList)
    }
}
