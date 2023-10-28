package ru.otus.hw

class PropertiesReader {

    Properties properties

    PropertiesReader(){
        properties = new Properties()
        properties.load(getClass().getClassLoader().getResourceAsStream('app.properties'))
    }

    def getProperties(){
        this.properties
    }
}
