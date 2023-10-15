package ru.otus

import groovy.xml.MarkupBuilder
import org.junit.jupiter.api.Test
import ru.otus.prepare.JsonContentPrepare
import ru.otus.service.impl.HtmlParsingServiceImpl

class HtmlParsingServiceImplTest {

    @Test
    void parsingResultWorkWithOutExceptions(){
        JsonContentPrepare jsonContentPrepare = new JsonContentPrepare()
        jsonContentPrepare.setFileURL('https://github.com/Groovy-Developer/groovy-2023-07-hw/raw/main/test.json')
        jsonContentPrepare.createByURL()
        HtmlParsingServiceImpl htmlParsingService = new HtmlParsingServiceImpl()
        MarkupBuilder builder = htmlParsingService.createAndPrint(jsonContentPrepare.getResult())
        assert builder != null
    }
}
