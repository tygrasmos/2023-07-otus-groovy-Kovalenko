package ru.otus

import groovy.xml.MarkupBuilder
import org.junit.jupiter.api.Test
import ru.otus.prepare.JsonContentPrepare
import ru.otus.service.impl.XmlParsingServiceImpl

class XmlParsingServiceImplTest {

    @Test
    void parsingResultWorkWithOutExceptions(){
        JsonContentPrepare jsonContentPrepare = new JsonContentPrepare()
        jsonContentPrepare.setFileURL('https://github.com/Groovy-Developer/groovy-2023-07-hw/raw/main/test.json')
        jsonContentPrepare.createByURL()
        XmlParsingServiceImpl xmlParsingService = new XmlParsingServiceImpl()
        MarkupBuilder builder = xmlParsingService.createAndPrint(jsonContentPrepare.getResult())
        assert builder != null
    }

}
