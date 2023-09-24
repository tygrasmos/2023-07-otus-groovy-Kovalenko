package ru.otus.atm.exception

class CFTIntegrationException extends RuntimeException{

    public CFTIntegrationException(){
    }

    public CFTIntegrationException(String message){
        super(message)
    }

    public CFTIntegrationException(String message, Throwable cause){
        super(message, cause)
    }

    public CFTIntegrationException(Throwable cause){
        super(cause)
    }
}
