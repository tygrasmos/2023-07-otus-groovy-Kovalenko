package ru.otus.hw.repository

import groovy.transform.Canonical
import ru.otus.hw.ReflectionUtility
import ru.otus.hw.mapper.EntityClassMetaData
import ru.otus.hw.mapper.EntityClassMetaDataImpl
import ru.otus.hw.mapper.EntitySQLMetaData
import ru.otus.hw.mapper.EntitySQLMetaDataImpl
import ru.otus.hw.mapper.ResultListMapper

import java.sql.Connection
import java.sql.ResultSet
import java.util.stream.Collectors

@Canonical
class DataTemplateJdbc implements DataTemplate {
    private DbExecutor dbExecutor
    private EntitySQLMetaData entitySQLMetaData
    private EntityClassMetaData entityClassMetaData

    @Override
    def findById(Object connection, long id) {
        List res = new ArrayList()
        dbExecutor.executeSelect((Connection) connection,
                entitySQLMetaData.getSelectAllSql(),
                new ArrayList<Object>(),
                {
                   def mapper = new ResultListMapper()
                   res = mapper.apply(it) as List
                }).get()

        res.stream().findFirst()
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
                getParams(object))
    }

    @Override
    void update( connection,  object) {
        dbExecutor.executeStatement((Connection) connection,
                entitySQLMetaData.getUpdateSql(),
                getParams(object))
    }

    List<Object> getParams( object){
        List<Object> params = new ArrayList<>()
        entityClassMetaData.getAllFields().each {params.add(ReflectionUtility.getValueFromObjectByField(it, object))}
        params
    }
}
