package ru.otus.atm.exception

class ExceptionFromClient extends RuntimeException{

    public ExceptionFromClient(){
    }

    public ExceptionFromClient(String message){
        super(message)
    }

    public ExceptionFromClient(String message, Throwable cause){
        super(message, cause)
    }

    public ExceptionFromClient(Throwable cause){
        super(cause)
    }
}
