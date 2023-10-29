package ru.otus.mapper

import org.junit.jupiter.api.Test
import ru.otus.annotation.Id
import ru.otus.model.Client

import java.util.stream.Collectors

class EntityClassMetaDataImplTest {

    EntityClassMetaDataImpl impl

    EntityClassMetaDataImplTest(){
        this.impl = new EntityClassMetaDataImpl<>(Client.class)
    }

    @Test
    void methodReturnedEntityNameMustBeCorrect(){
        assert impl.getName() == "Client"
    }

    @Test
    void methodReturnedAllFieldsMustBeCorrect(){
        assert impl.getAllFields() ==
                Client.class.getDeclaredFields().toList().stream()
                        .filter(f -> f.modifiers == 2)
                        .collect(Collectors.toList())
    }

    @Test
    void methodReturnedAllFieldsWithOutIdFieldMustBeCorrect(){
        assert impl.getWithoutIdFields() ==
                Client.class.getDeclaredFields().toList().stream()
                        .filter(f -> (f.modifiers == 2 && !f.isAnnotationPresent(Id.class)))
                        .collect(Collectors.toList())
    }

    @Test
    void methodReturnedOnlyIdFieldMustBeCorrect(){
        assert impl.getIdField() ==
                Client.class.getDeclaredFields().toList().stream()
                        .filter(f -> (f.modifiers == 2 && f.isAnnotationPresent(Id.class)))
                        .collect(Collectors.toList())
                        .first()
    }
}
