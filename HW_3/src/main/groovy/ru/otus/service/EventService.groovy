package ru.otus.service

import ru.otus.model.Event

interface EventService {

    def fireEvent(Event event)

}