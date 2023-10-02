package ru.otus.service.impl

import org.springframework.stereotype.Service
import ru.otus.model.Task
import ru.otus.service.TaskService

import java.time.LocalDateTime

@Service
class TaskServiceImpl implements TaskService{

    @Override
    Task create(Task task) {
        if(validate(task)){
            return save(task)
        } else {
            throw new RuntimeException("Выберите другое время выполнения задачи.")
        }
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

    Boolean validate(Task task){
        def startTime = task.getDateTimeStartTask()
        def endTime = task.getDateTimeEndTask()
        getAll().findAll() {
            it.getDateTimeStartTask() <= startTime
            it.getDateTimeEndTask() >= endTime
        }.collect().size() == 0
    }



}
