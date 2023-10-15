package ru.otus.service

import groovy.xml.MarkupBuilder

interface ParsingService {

    MarkupBuilder createAndSave(Object o)

    MarkupBuilder createAndPrint(Object o)

}