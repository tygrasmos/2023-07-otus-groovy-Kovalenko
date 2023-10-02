package ru.otus

import org.springframework.stereotype.Service
import ru.otus.service.ActionService
import ru.otus.service.TaskService

@Service
class CalendarService {

    private final ActionService actionService
    private final TaskService taskService

    public CalendarService(ActionService actionService,
                           TaskService taskService){
        this.actionService = actionService
        this.taskService = taskService
    }


}
