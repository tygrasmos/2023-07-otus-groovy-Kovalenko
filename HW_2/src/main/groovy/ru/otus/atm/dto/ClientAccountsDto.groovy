package ru.otus.atm.dto

import ru.otus.atm.entity.Banknote
import ru.otus.atm.model.Account

class ClientAccountsDto {

    List<Account> accounts
    List<Banknote> banknotes
    Long clientId
    String error

}
