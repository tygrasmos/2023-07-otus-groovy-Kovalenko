package ru.otus.service

import ru.otus.model.Action

import java.time.LocalDateTime

interface ActionService {

    Action create(Action action)

    Action get(LocalDateTime time)

    List<Action> getAll()

    Action update(Action action)

    def delete(Action action)

    def deleteAll(List<Action> actions)

}
