package ru.otus.atm.integration

import ru.otus.atm.model.Account

interface CFTIntegration {

    List<Account> getAccounts(Long clientId)

    List<Account> updateAccounts(List<Account> accounts, Long clientId)

}
