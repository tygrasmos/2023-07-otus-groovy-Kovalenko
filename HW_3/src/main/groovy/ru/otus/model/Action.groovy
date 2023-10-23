package ru.otus.model

import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class Action {

    private LocalDateTime dateTimeStartAction
    private LocalDateTime dateTimeEndAction
    private Long actionDuration
    private String actionName

    LocalDateTime getDateTimeStartAction() {
        return dateTimeStartAction
    }

    void setDateTimeStartAction(LocalDateTime dateTimeStartAction) {
        this.dateTimeStartAction = dateTimeStartAction
    }

    LocalDateTime getDateTimeEndAction() {
        return dateTimeEndAction
    }

    void setDateTimeEndAction(LocalDateTime dateTimeEndAction) {
        this.dateTimeEndAction = dateTimeEndAction
    }

    Long getActionDuration() {
        return actionDuration
    }

    void setActionDuration(Long actionDuration) {
        this.actionDuration = actionDuration
    }

    String getActionName() {
        return actionName
    }

    void setActionName(String actionName) {
        this.actionName = actionName
    }
}
