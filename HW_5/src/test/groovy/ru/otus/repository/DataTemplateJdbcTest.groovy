package ru.otus.repository

import org.junit.jupiter.api.Test
import ru.otus.mapper.EntityClassMetaDataImpl
import ru.otus.mapper.EntitySQLMetaDataImpl
import ru.otus.model.Client

class DataTemplateJdbcTest {

    DataTemplateJdbc dataTemplateClient

    DataTemplateJdbcTest(){
        def entityClassMetaDataClient = new EntityClassMetaDataImpl<>(Client.class)
        def entitySQLMetaDataClient = new EntitySQLMetaDataImpl<>(entityClassMetaDataClient)
        this.dataTemplateClient = new DataTemplateJdbc(
                dbExecutor: new DbExecutorImpl(),
                entitySQLMetaData: entitySQLMetaDataClient,
                entityClassMetaData: entityClassMetaDataClient)
    }

    @Test
    void methodReturnedParamListMustBeCorrect(){
        assert dataTemplateClient.getParams(getClient()) == List.of(1L, "Name")
    }

    @Test
    void methodReturnedParamListWithIdFieldAtTheEndEndMustBeCorrect(){
        assert dataTemplateClient.getParamsForUpdate(getClient()) == List.of("Name", 1L)
    }

    static Client getClient(){
        Client client = new Client()
        client.setId(1L)
        client.setName("Name")
        client
    }
}
