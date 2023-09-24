package ru.otus.atm.service.impl

import org.springframework.stereotype.Service
import ru.otus.atm.dto.ClientAccountsDto
import ru.otus.atm.dto.ClientAccountsDtoMapper
import ru.otus.atm.entity.Banknote
import ru.otus.atm.exception.CRMIntegrationException
import ru.otus.atm.exception.ExceptionFromClient
import ru.otus.atm.integration.CFTIntegration
import ru.otus.atm.integration.CRMIntegration
import ru.otus.atm.model.Account
import ru.otus.atm.model.Client
import ru.otus.atm.service.ClientService
import ru.otus.atm.service.ProcessingService


@Service
class ClientServiceImpl implements ClientService{

    private final CFTIntegration cftIntegration
    private final CRMIntegration crmIntegration
    private final ProcessingService processingService
    private final ClientAccountsDtoMapper mapper

    ClientServiceImpl(CFTIntegration cftIntegration,
                      CRMIntegration crmIntegration,
                      ProcessingService processingService,
                      ClientAccountsDtoMapper mapper){

        this.cftIntegration = cftIntegration
        this.crmIntegration = crmIntegration
        this.processingService = processingService
        this.mapper = mapper
    }


    @Override
    ClientAccountsDto getClient(Integer cardNumber, Integer pin) {
        try{
            Client client = getClientCRM(cardNumber, pin)
            List<Account> accounts = getAccountsCFT(client)
            return mapper.getClientAccountsDto(accounts, new ArrayList<>(), null)
        } catch (Exception e){
            return mapper.getClientAccountsDto(new ArrayList<>(), new ArrayList<>(), e.getMessage())
        }

    }

    @Override
    ClientAccountsDto withdrawalCash(Long amount, String currencyIdent, ClientAccountsDto dto) {
        try {
            List<Banknote> banknotes = processingService.getBanknotes(amount, currencyIdent)
            dto.getAccounts().forEach(
                    {
                        if (it.getCurrency().getCurrencyIdent() == currencyIdent) {
                            def oldAmount = it.getAmount()
                            it.setAmount(oldAmount - amount)
                        }
                    })
            updateAccountsCFT(dto.getAccounts(), dto.getClientId())
            dto.setBanknotes(banknotes)
            dto
        } catch (Exception e){
            dto.setError(e.getMessage())
        }
    }

    @Override
    ClientAccountsDto acceptanceCash(String currencyIdent, ClientAccountsDto dto) {
        try {
            Long amount = processingService.addBanknotes(currencyIdent, dto.getBanknotes())
            dto.getAccounts().forEach(
                    {
                        if (it.getCurrency().getCurrencyIdent() == currencyIdent) {
                            def oldAmount = it.getAmount()
                            it.setAmount(oldAmount + amount)
                        }
                    })
            updateAccountsCFT(dto.getAccounts(), dto.getClientId())
            dto.setBanknotes(new ArrayList<Banknote>())
            dto
        } catch (Exception e){
            dto.setError(e.getMessage())
        }
    }

    /** Получим клиента по его карте и пин коду */
    private Client getClientCRM(Integer cardNumber, Integer pin){
        try {
            return crmIntegration.getClient(cardNumber, pin)
        } catch (CRMIntegrationException e){
            throw new ExceptionFromClient(e.getMessage(), e.getCause())
        } catch (Exception e){
            throw new ExceptionFromClient(e.getMessage(), e.getCause())
        }
    }

    /** Получим список счетов для заданного клиента */
    private List<Account> getAccountsCFT(Client client){
        try {
            return cftIntegration.getAccounts(client.getClientId())
        } catch (CRMIntegrationException e){
            throw new ExceptionFromClient(e.getMessage(), e.getCause())
        } catch (Exception e){
            throw new ExceptionFromClient(e.getMessage(), e.getCause())
        }
    }

    /** Передадим обновленные суммы счетов по заданному клиенту */
    private List<Account> updateAccountsCFT(List<Account> accounts, Long clientId){
        try {
            return cftIntegration.updateAccounts(accounts, clientId)
        } catch (CRMIntegrationException e){
            throw new ExceptionFromClient(e.getMessage(), e.getCause())
        } catch (Exception e){
            throw new ExceptionFromClient(e.getMessage(), e.getCause())
        }
    }















}
