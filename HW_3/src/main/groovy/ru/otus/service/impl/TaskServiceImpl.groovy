package ru.otus.service.impl

import org.springframework.stereotype.Service
import ru.otus.model.Task
import ru.otus.service.TaskService

import java.time.LocalDateTime

@Service
class TaskServiceImpl implements TaskService{

    TaskServiceImpl(){

    }

    @Override
    Task create(Task task) {
        return null
    }

    @Override
    Task get(LocalDateTime time) {
        return null
    }

    @Override
    List<Task> getAll() {
        return null
    }

    @Override
    Task update(Task task) {
        return null
    }

    @Override
    def delete(Task task) {
        return null
    }

}
