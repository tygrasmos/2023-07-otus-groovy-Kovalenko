package ru.otus.atm.integration.impl

import org.springframework.stereotype.Service
import ru.otus.atm.exception.CRMIntegrationException
import ru.otus.atm.integration.CRMIntegration
import ru.otus.atm.model.Client

@Service
class CRMIntegrationImpl implements CRMIntegration{

    @Override
    Client getClient(Integer cardNumber, Integer pin) {
        try{
            return new Client()
        } catch (Exception e){
            throw new CRMIntegrationException("Ошибка получения клиента из CRM.", e.getCause())
        }
    }
}
