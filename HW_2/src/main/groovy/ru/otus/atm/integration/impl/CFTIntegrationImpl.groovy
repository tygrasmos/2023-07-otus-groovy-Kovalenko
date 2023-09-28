package ru.otus.atm.integration.impl

import org.springframework.stereotype.Service
import ru.otus.atm.exception.CRMIntegrationException
import ru.otus.atm.integration.CFTIntegration
import ru.otus.atm.model.Account

@Service
class CFTIntegrationImpl implements CFTIntegration{

    @Override
    List<Account> getAccounts(Long clientId) {
        try{
            return new ArrayList<>()
        } catch (Exception e){
            throw new CRMIntegrationException("Ошибка получения информации о счетах клиента с ID = "
                    .concat(clientId.toString())
                    .concat("."), e.getCause())
        }
    }

    @Override
    List<Account> updateAccounts(List<Account> accounts, Long clientId) {
        try{
            return new ArrayList<>()
        } catch (Exception e){
            throw new CRMIntegrationException("Ошибка сохранения измененной информации о счетах клиента с ID = "
                    .concat(accounts.stream().findFirst().get().getClientId().toString())
                    .concat("."), e.getCause())
        }
    }
}
