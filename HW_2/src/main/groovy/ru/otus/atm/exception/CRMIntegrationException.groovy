package ru.otus.atm.exception

class CRMIntegrationException extends RuntimeException{

    public CRMIntegrationException(){
    }

    public CRMIntegrationException(String message){
        super(message)
    }

    public CRMIntegrationException(String message, Throwable cause){
        super(message, cause)
    }

    public CRMIntegrationException(Throwable cause){
        super(cause)
    }
}
