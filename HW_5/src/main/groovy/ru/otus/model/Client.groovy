package ru.otus.model

import groovy.transform.ToString
import ru.otus.annotation.Id

@ToString(includeFields = true)
class Client {
    @Id
    private Long id
    private String name

    Client() {
    }

    Client(String name) {
        this.id = null
        this.name = name
    }

    Client(Long id, String name) {
        this.id = id
        this.name = name
    }

    Long getId() {
        return id
    }

    String getName() {
        return name
    }

    void setId(Long id) {
        this.id = id
    }

    void setName(String name) {
        this.name = name
    }
}
