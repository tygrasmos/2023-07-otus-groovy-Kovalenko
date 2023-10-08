package ru.otus

import groovy.json.JsonSlurper
import org.springframework.stereotype.Service


@Service
class JsonContentPrepare {

    private String filePath
    private String fileURL
    private Object result

    def createByFile(){
        setJsonParseResult(createFileInputStream(createFileByLocalPath()))
    }

    def createByURL(){
        setJsonParseResult(null)
    }

    private def createFileByLocalPath(){
        new File(getClass().getResource(getFilePath()).toURI())
    }

    private static def createFileInputStream(File file){
        new FileInputStream(file)
    }

    private def setJsonParseResult(InputStream inputStream){
        def jsonRes = new JsonSlurper()
        if (inputStream != null){
            this.result = jsonRes.parse(inputStream)
        } else {
            this.result = jsonRes.parse(fileURL.toURL())
        }
    }

    String getFilePath() {
        return filePath
    }

    void setFilePath(String filePath) {
        this.filePath = filePath
    }

    Object getResult() {
        if (result == null){
            throw new RuntimeException("Контекст не создан.")
        } else {
            return result
        }
    }

    String getFileURL() {
        return fileURL
    }

    void setFileURL(String fileURL) {
        this.fileURL = fileURL
    }
}
