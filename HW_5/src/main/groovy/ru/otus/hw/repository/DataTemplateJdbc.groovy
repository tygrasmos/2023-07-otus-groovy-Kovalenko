package ru.otus.hw.repository

import groovy.transform.Canonical
import ru.otus.hw.ReflectionUtility
import ru.otus.hw.mapper.EntityClassMetaData
import ru.otus.hw.mapper.EntitySQLMetaData
import ru.otus.hw.mapper.ResultListMapper

import java.sql.Connection

@Canonical
class DataTemplateJdbc implements DataTemplate {
    private DbExecutor dbExecutor
    private EntitySQLMetaData entitySQLMetaData
    private EntityClassMetaData entityClassMetaData

    @Override
    def findById(Object connection, long id) {
        List res = new ArrayList()
        dbExecutor.executeSelect((Connection) connection,
                entitySQLMetaData.getSelectByIdSql(),
                List.of(id),
                {
                   def mapper = new ResultListMapper(entityClassMetaData)
                   res = mapper.apply(it) as List
                })

        res.stream().findFirst()
    }

    @Override
    List findAll( connection) {
        List res = new ArrayList()
        dbExecutor.executeSelect((Connection) connection,
                entitySQLMetaData.getSelectAllSql(),
                new ArrayList<Object>(),
                {
                    def mapper = new ResultListMapper(entityClassMetaData)
                    res = mapper.apply(it) as List
                }).get()
        res
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
                getParamsForUpdate(object))
    }

    List<Object> getParams( object) {
        List<Object> params = new ArrayList<>()
        entityClassMetaData.getAllFields().each {params.add(ReflectionUtility.getValueFromObjectByField(it, object))}
        params
    }

    List<Object> getParamsForUpdate( object) {
        List<Object> params = new ArrayList<>()
        entityClassMetaData.getFieldsWithoutId().each {params.add(ReflectionUtility.getValueFromObjectByField(it, object))}
        params.add(ReflectionUtility.getValueFromObjectByField(entityClassMetaData.getIdField(), object)) as List<Object>
        params
    }
}
