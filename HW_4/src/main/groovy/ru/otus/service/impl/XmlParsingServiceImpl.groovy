package ru.otus.service.impl

import groovy.xml.MarkupBuilder
import org.springframework.stereotype.Service
import ru.otus.service.ParsingService
import ru.otus.util.Utilities

@Service
class XmlParsingServiceImpl implements ParsingService{

    /**
     * <persons>
     *     <person>
     *         <name>Пупкин Морква Свеклович</name>
     *         <age>22</age>
     *         <secretIdentity>322-223</secretIdentity>
     *         <powers>
     *             <power>100</power>
     *             <power>50</power>
     *             <power>70</power>
     *         </powers>
     *     </person>
     * </persons>
     * */

    @Override
    MarkupBuilder createAndSave(Object o) {
        def writer = new FileWriter('./output/persons.xml')
        def builder = new MarkupBuilder(writer)
        parseInXml(o, builder)
        builder
    }

    @Override
    MarkupBuilder createAndPrint(Object o) {
        def builder = new MarkupBuilder()
        parseInXml(o, builder)
        println(builder.toString())
        builder
    }

    static def parseInXml(Object o, MarkupBuilder builder){
        LinkedHashMap dataMap = Utilities.trueSorted(o)
        builder.persons{
            if (!dataMap.isEmpty()) {
                person {
                    dataMap.forEach({ k, v ->
                        String keyName = k.toString()
                        if (Utilities.isCollection(v)) {
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

}
