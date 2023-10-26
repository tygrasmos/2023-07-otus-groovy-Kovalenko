package ru.otus.hw.mapper

import ru.otus.hw.annotation.Id
import ru.otus.hw.exception.EntityMetaDataException

import java.lang.reflect.Constructor
import java.lang.reflect.Field

class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    Class<T> clazz

    Field idField
    List<Field> withoutIdFiles
    Constructor<T> constructor

    EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz
        this.constructor = findFirstConstructorWithOutParameters()
    }

    def findIdField() {
        for (def field in clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(Id.class)) {
                return true;
            }
        }
        throw new EntityMetaDataException("@id not found for class ${getName()}")
    }

    def findFirstConstructorWithOutParameters() {
        return clazz.getConstructor()
    }

    @Override
    String getName() {
        clazz.getName()
    }

    @Override
    List<Field> getAllFields() {
        clazz.getDeclaredFields().findAll()
    }

    @Override
    List<Field> getFieldsWithoutId() {
        if(findIdField()){
            getAllFields().sort( f -> {
                !f.isAnnotationPresent(Id.class)
            })
        } else {
            getAllFields()
        }
    }
}
