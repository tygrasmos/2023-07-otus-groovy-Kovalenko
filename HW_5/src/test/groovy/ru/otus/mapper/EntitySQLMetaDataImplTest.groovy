package ru.otus.mapper

import org.junit.jupiter.api.Test
import ru.otus.model.Client

class EntitySQLMetaDataImplTest {

    EntitySQLMetaDataImpl sqlImpl

    EntitySQLMetaDataImplTest(){
        EntityClassMetaDataImpl impl = new EntityClassMetaDataImpl<>(Client.class)
        this.sqlImpl = new EntitySQLMetaDataImpl<>(impl)
    }

    @Test
    void QuerySelectAllMustBeCorrect(){
        assert sqlImpl.getSelectAllSql() == "SELECT * FROM Client"
    }

    @Test
    void QuerySelectByIdMustBeCorrect(){
        assert sqlImpl.getSelectByIdSql() == "SELECT * FROM Client WHERE id = ?"
    }

    @Test
    void QueryUpdateMustBeCorrect(){
        assert sqlImpl.getUpdateSql() == "UPDATE Client SET name = ? WHERE id = ?"
    }

    @Test
    void QueryInsertMustBeCorrect(){
        assert sqlImpl.getInsertSql() == "INSERT INTO Client (id,name) VALUES(?,?)"
    }

}
