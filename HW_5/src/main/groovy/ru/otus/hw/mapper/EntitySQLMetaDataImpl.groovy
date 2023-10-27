package ru.otus.hw.mapper

import groovy.transform.Canonical

import java.util.stream.Collectors

@Canonical
class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    private final EntityClassMetaData<T> entityClassMetaData

    private final String UPDATE_CHARS = ' = ? '
    private final String INSERT_CHARS = ' ?'
    private final String WHERE_CHARS = '= ?'

    EntitySQLMetaDataImpl(entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData
    }

    @Override
    String getSelectAllSql() {
        "SELECT * FROM ${entityClassMetaData.getName()}"
    }

    @Override
    String getSelectByIdSql() {
        return "SELECT * FROM ${entityClassMetaData.getName()} WHERE ${entityClassMetaData.getIdField()} " + WHERE_CHARS
    }

    @Override
    String getInsertSql() {
        "INSERT INTO ${entityClassMetaData.getName()} " +
                "(${getFieldsName()}) " +
                "VALUES(${getInsertValues(INSERT_CHARS)})"
    }

    @Override
    String getUpdateSql() {
        "UPDATE ${entityClassMetaData.getName()} " +
                "SET ${getUpdateValues(UPDATE_CHARS)} " +
                "WHERE ${entityClassMetaData.getIdField()} " + WHERE_CHARS
    }

    String getInsertValues(String value){
        entityClassMetaData.getAllFields().stream()
                .map(field -> value)
                .collect(Collectors.joining(','))
    }

    String getUpdateValues(String value){
        entityClassMetaData.getAllFields().stream()
                .map(field -> field.getName().concat(value))
                .collect(Collectors.joining(','))
    }

    String getFieldsName(){
        entityClassMetaData.getAllFields().stream()
                .map(field -> field.getName())
                .collect(Collectors.joining(','))
    }
}
