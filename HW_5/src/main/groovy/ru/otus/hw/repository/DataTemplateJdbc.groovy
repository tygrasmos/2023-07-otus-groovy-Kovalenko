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

        return null
    }

    @Override
    List findAll( connection) {
        connection
    }

    @Override
    long insert( connection,  object) {
        setFields(object)
        dbExecutor.executeStatement((Connection) connection,
                entitySQLMetaData.getInsertSql(),
                entityClassMetaData.getFieldsWithoutId())
    }

    @Override
    void update( connection,  object) {
        setFields(object)
        connection {
            dbExecutor.executeStatement((Connection) connection,
                    entitySQLMetaData.getUpdateSql(),
                    entityClassMetaData.getAllFields())
        }
    }

    def setFields(  object){
        entityClassMetaData = new EntityClassMetaDataImpl(object)
        entitySQLMetaData = new EntitySQLMetaDataImpl(entityClassMetaData)
    }
}
