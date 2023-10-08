package ru.otus.model

import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class Event {

    private String eventName
    private LocalDateTime dateTimeStart

    String getEventName() {
        return eventName
    }

    void setEventName(String eventName) {
        this.eventName = eventName
    }

    LocalDateTime getDateTimeStart() {
        return dateTimeStart
    }

    void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart
    }
}
