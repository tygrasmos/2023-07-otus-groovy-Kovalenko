package ru.otus.hw.mapper

import ru.otus.hw.annotation.Id
import ru.otus.hw.exception.EntityMetaDataException

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.util.stream.Collectors

class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    Class<T> clazz

    Field idField
    List<Field> withoutIdFields
    Constructor<T> constructor

    EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz
        this.constructor = findFirstConstructorWithOutParameters()
        this.withoutIdFields = clazz.getDeclaredFields().findAll().stream()
                .filter( f -> {
                    f.getModifiers() == 2 && !f.isAnnotationPresent(Id.class)
                }).collect(Collectors.toList())
        this.idField = clazz.getDeclaredFields().findAll().stream()
                .filter( f -> {
                    f.getModifiers() == 2 && f.isAnnotationPresent(Id.class)
                }).collect(Collectors.toList())
                .first()
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
        getEntityName(this.clazz)
    }

    @Override
    List<Field> getAllFields() {
        clazz.getDeclaredFields().findAll().stream()
                .filter( f -> {f.getModifiers() == 2})
                .collect(Collectors.toList())
    }

    @Override
    List<Field> getFieldsWithoutId() {
        this.withoutIdFields
    }

    static def getEntityName(Class<T> clazz){
        String[] str = clazz.getName().split('\\.')
        StringBuilder name = new StringBuilder()
        for (int i = 1; i <= str.length; i++) {
            if (i == str.length - 1) {
                name.append(str[i])
            }
        }
        name
    }
}
