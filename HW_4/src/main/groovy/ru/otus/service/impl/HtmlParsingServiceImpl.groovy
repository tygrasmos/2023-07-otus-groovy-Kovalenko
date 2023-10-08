package ru.otus.service.impl

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
        return null
    }

    @Override
    def createAndPrint(Object o) {
        return null
    }
}
