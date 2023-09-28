package ru.otus.atm.dto

import org.springframework.stereotype.Service
import ru.otus.atm.entity.Banknote
import ru.otus.atm.model.Account

@Service
class ClientAccountsDtoMapper {

   ClientAccountsDto getClientAccountsDto(List<Account> accounts, List<Banknote> banknotes, String error){
       ClientAccountsDto dto = new ClientAccountsDto()
       dto.setError(error)
       dto.setBanknotes(banknotes)
       dto.setAccounts(accounts)
       dto.setClientId(accounts.isEmpty() ? null : accounts.stream().findFirst().get().getClientId())
   }

}
