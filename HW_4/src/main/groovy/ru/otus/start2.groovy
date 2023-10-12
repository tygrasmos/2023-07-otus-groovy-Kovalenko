package ru.otus

import ru.otus.service.impl.HtmlParsingServiceImpl
import ru.otus.service.impl.XmlParsingServiceImpl

/*def rrr = new JsonContentPrepare()



rrr.createByURL()
rrr.createByFile()
rrr.getResult()*/

def parseService = new ParseService(new JsonContentPrepare(), new HtmlParsingServiceImpl(), new XmlParsingServiceImpl())


parseService.getResult()

