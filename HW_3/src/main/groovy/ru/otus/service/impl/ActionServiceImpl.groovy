package ru.otus.service.impl

import org.springframework.stereotype.Service
import ru.otus.model.Action
import ru.otus.service.ActionService

import java.time.LocalDateTime

@Service
class ActionServiceImpl implements ActionService{

    ActionServiceImpl(){

    }

    @Override
    Action create(Action action) {
        return null
    }

    @Override
    Action get(LocalDateTime time) {
        return null
    }

    @Override
    List<Action> getAll() {
        return null
    }

    @Override
    Action update(Action action) {
        return null
    }

    @Override
    def delete(Action action) {
        return null
    }

    @Override
    def deleteAll(List<Action> actions) {
        return null
    }
}
