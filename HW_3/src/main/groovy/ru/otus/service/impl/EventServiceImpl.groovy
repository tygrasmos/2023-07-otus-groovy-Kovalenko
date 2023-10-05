package ru.otus.service.impl

import org.springframework.stereotype.Service
import ru.otus.model.Event
import ru.otus.service.EventService

@Service
class EventServiceImpl implements EventService{

    @Override
    def fireEvent(Event event) {
        println(event.getEventName())
        println(event.getDateTimeStart())
    }
}
