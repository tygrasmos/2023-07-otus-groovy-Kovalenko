package ru.otus.service.impl

import groovy.xml.MarkupBuilder
import ru.otus.service.ParsingService

class XmlParsingServiceImpl implements ParsingService{

    /**
     * <persons>
     *     <person name = 'Пупкин Морква Свеклович'>
     *     <person age = 22/>
     *     <person secretIdentity = '322-223'/>
     *     <person powers>
     *         <power 100/>
     *         <power 50/>
     *         <power 70/>
     *     </powers>
     * </persons>
     * */

    @Override
    def createAndSave(Object o) {
        return null
    }

    @Override
    def createAndPrint(Object o) {
        return null
    }

    private parse(Object o){
        def builder = new MarkupBuilder()
    }

}
