package ru.otus.service

import org.springframework.stereotype.Service
import ru.otus.prepare.JsonContentPrepare
import ru.otus.service.impl.HtmlParsingServiceImpl
import ru.otus.service.impl.XmlParsingServiceImpl

@Service
class ParseService {

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

    def getResultParsingXmlAndSave(String path, String pathType){
        prepare(path, pathType)
        xmlParsingService.createAndSave(jsonContentPrepare.getResult())
    }

    def getResultParsingXmlAndPrint(String path, String pathType){
        prepare(path, pathType)
        xmlParsingService.createAndPrint(jsonContentPrepare.getResult())
    }

    def getResultParsingHtmlAndSave(String path, String pathType){
        prepare(path, pathType)
        htmlParsingService.createAndSave(jsonContentPrepare.getResult())
    }

    def getResultParsingHtmlAndPrint(String path, String pathType){
        prepare(path, pathType)
        htmlParsingService.createAndPrint(jsonContentPrepare.getResult())
    }

    def prepare(String path, String pathType){
        if(pathType == 'ByFile') {
            jsonContentPrepare.setFilePath(path)
            jsonContentPrepare.createByFile()
        } else if (pathType == 'ByURL') {
            jsonContentPrepare.setFileURL(path)
            jsonContentPrepare.createByURL()
        } else {
            throw  new RuntimeException('Парсинга с таким типом пути не существует')
        }
    }



}
