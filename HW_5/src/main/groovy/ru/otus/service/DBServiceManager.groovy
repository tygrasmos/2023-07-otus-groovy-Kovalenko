package ru.otus.service

import ru.otus.model.Manager


interface DBServiceManager {
    Manager saveManager(Manager client)
    Optional<Manager> getManager(long no)
    List<Manager> findAll()
}
