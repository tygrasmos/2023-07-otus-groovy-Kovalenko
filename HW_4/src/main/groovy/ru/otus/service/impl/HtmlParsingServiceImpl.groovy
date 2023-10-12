package ru.otus.service.impl

import groovy.xml.MarkupBuilder
import ru.otus.service.ParsingService

class HtmlParsingServiceImpl implements ParsingService{


    /**
     * <div>
        <div id="employee">
          <p>name</p><br/>
          <p>age</p><br/>
          <p>secretIdentity</p><br/>
          <ul id="powers">
            <li>power</li>
          </ul>
        </div>
       </div>
     * */


    @Override
    def createAndSave(Object o) {
        def writer = new FileWriter('./persons.html')
        def builder = new MarkupBuilder(writer)
        parseInHtml(o, builder)
    }

    @Override
    def createAndPrint(Object o) {
        def builder = new MarkupBuilder()
        parseInHtml(o, builder)
        println(builder.toString())
    }

    static def parseInHtml(Object o, MarkupBuilder builder){
        LinkedHashMap dataMap = (LinkedHashMap) o.collect().collectEntries()
        builder.html(lang : 'en') {
            head{
                meta(charset : 'UTF-8')
                title('Employee')
                link(href : '/main.css',type : 'text/css', rel : 'stylesheet')
            }
            body {
                div(class : 'textStyle') {
                    if (!dataMap.isEmpty()) {
                        div(class : 'textStyle', id: 'employee') {
                            dataMap.forEach({ k, v ->
                                if (isCollection(v)) {
                                    ul(id: 'powers') {
                                        List list = (ArrayList) v
                                        list.forEach({ el ->
                                            li(el)
                                        })
                                    }
                                } else {
                                    p(v)
                                }
                            })
                        }
                    }
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
