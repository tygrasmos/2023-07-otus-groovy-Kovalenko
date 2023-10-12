package ru.otus.service.impl

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class XmlParsingServiceImplTest {

    private final static List TEST_ARRAY = new ArrayList()

    @Autowired
    private XmlParsingServiceImpl xmlParsingService

    @Test
    void testByCollectionMustBeCorrect(){
        assert XmlParsingServiceImpl.isCollection(TEST_ARRAY)
    }
}
