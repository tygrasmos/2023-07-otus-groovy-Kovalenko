package ru.otus.atm.exception

class ProcessingException extends RuntimeException{

    public ProcessingException(){
    }

    public ProcessingException(String message){
        super(message)
    }

    public ProcessingException(String message, Throwable cause){
        super(message, cause)
    }

    public ProcessingException(Throwable cause){
        super(cause)
    }
}
