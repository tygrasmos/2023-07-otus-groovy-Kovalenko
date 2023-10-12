package ru.otus.service.impl

import groovy.xml.MarkupBuilder
import ru.otus.service.ParsingService

class XmlParsingServiceImpl implements ParsingService{

    /**
     * <persons>
     *     <person>
     *         <age>22</age>
     *         <name>Пупкин Морква Свеклович</name>
     *         <powers>
     *             <power>100</power>
     *             <power>50</power>
     *             <power>70</power>
     *         </powers>
     *         <secretIdentity>322-223</secretIdentity>
     *     </person>
     * </persons>
     * */

    @Override
    def createAndSave(Object o) {
        def writer = new FileWriter('./persons.xml')
        def builder = new MarkupBuilder(writer)
        parseInXml(o, builder)
    }

    @Override
    def createAndPrint(Object o) {
        def builder = new MarkupBuilder()
        parseInXml(o, builder)
        println(builder.toString())
    }

    static def parseInXml(Object o, MarkupBuilder builder){
        LinkedHashMap dataMap = (LinkedHashMap) o.collect().collectEntries()
        builder.persons{
            if (!dataMap.isEmpty()) {
                person {
                    dataMap.forEach({ k, v ->
                        String keyName = k.toString()
                        if (isCollection(v)) {
                            powers{
                                    List list = (ArrayList) v
                                    list.forEach( { el ->
                                        power(el)
                                    })
                            }
                        } else {
                            if (keyName == 'age') {
                                age(v)
                            } else if (keyName == 'name') {
                                name(v)
                            } else if (keyName == 'secretIdentity') {
                                secretIdentity(v)
                            }
                        }
                    })
                }
            }
        }
    }

    static boolean isCollection(Object o){
        if (o.getClass() == ArrayList.class){
            true
        } else {
            false
        }
    }

}
