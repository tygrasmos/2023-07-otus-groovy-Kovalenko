package ru.otus.hw.repository

import groovy.transform.Canonical
import ru.otus.hw.mapper.EntityClassMetaData
import ru.otus.hw.mapper.EntityClassMetaDataImpl
import ru.otus.hw.mapper.EntitySQLMetaData
import ru.otus.hw.mapper.EntitySQLMetaDataImpl

import java.sql.Connection

@Canonical
class DataTemplateJdbc implements DataTemplate {
    private DbExecutor dbExecutor
    private EntitySQLMetaData entitySQLMetaData
    private EntityClassMetaData entityClassMetaData

    @Override
    def findById(Object connection, long id) {
        def result = new Object()
        dbExecutor.executeSelect((Connection) connection,
                entitySQLMetaData.getSelectAllSql(),
                new ArrayList<Object>(),
                {
                    result = it
                }).get()

        (entityClassMetaData.getClass()) result
    }

    @Override
    List findAll( connection) {
        List result = new ArrayList()
        dbExecutor.executeSelect((Connection) connection,
                entitySQLMetaData.getSelectAllSql(),
                new ArrayList<Object>(),
                {
                    result = it
                }).get()
        result
    }

    @Override
    long insert( connection,  object) {
        dbExecutor.executeStatement((Connection) connection,
                entitySQLMetaData.getInsertSql(),
                entityClassMetaData.getAllFields())
    }

    @Override
    void update( connection,  object) {
        dbExecutor.executeStatement((Connection) connection,
                entitySQLMetaData.getUpdateSql(),
                entityClassMetaData.getAllFields())
    }

    def getParams(Object object){
        object.getMetaPropertyValues().findAll().stream()
        //.filter(v -> v.get)
    }
}
