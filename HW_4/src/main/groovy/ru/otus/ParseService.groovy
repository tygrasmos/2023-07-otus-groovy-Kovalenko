package ru.otus

import org.springframework.stereotype.Service
import ru.otus.service.impl.HtmlParsingServiceImpl
import ru.otus.service.impl.XmlParsingServiceImpl

@Service
class ParseService {

    private final static String filePath = '/test.json'
    private final static String fileURL = 'https://github.com/Groovy-Developer/groovy-2023-07-hw/raw/main/test.json'

    private final JsonContentPrepare jsonContentPrepare
    private final HtmlParsingServiceImpl htmlParsingService
    private final XmlParsingServiceImpl xmlParsingService

    ParseService(JsonContentPrepare jsonContentPrepare,
                 HtmlParsingServiceImpl htmlParsingService,
                 XmlParsingServiceImpl xmlParsingService){
        this.jsonContentPrepare = jsonContentPrepare
        this.htmlParsingService = htmlParsingService
        this.xmlParsingService = xmlParsingService
    }

    Object getResult(){
        jsonContentPrepare.setFilePath(filePath)
        jsonContentPrepare.setFileURL(fileURL)
        jsonContentPrepare.createByURL()
      //  xmlParsingService.createAndSave(jsonContentPrepare.getResult())
      //  xmlParsingService.createAndPrint(jsonContentPrepare.getResult())
        htmlParsingService.createAndSave(jsonContentPrepare.getResult())
      //  htmlParsingService.createAndPrint(jsonContentPrepare.getResult())
    }


}
