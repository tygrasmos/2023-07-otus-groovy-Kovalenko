package ru.otus

import ru.otus.prepare.JsonContentPrepare
import ru.otus.service.ParseService
import ru.otus.service.impl.HtmlParsingServiceImpl
import ru.otus.service.impl.XmlParsingServiceImpl

final String filePath = '/test.json'
final String fileURL = 'https://github.com/Groovy-Developer/groovy-2023-07-hw/raw/main/test.json'
final String BY_FILE_IDENT = 'ByFile'
final String BY_URL_IDENT = 'ByURL'

def parseService = new ParseService(new JsonContentPrepare(), new HtmlParsingServiceImpl(), new XmlParsingServiceImpl())

parseService.getResultParsingXmlAndSave(filePath, BY_FILE_IDENT)

