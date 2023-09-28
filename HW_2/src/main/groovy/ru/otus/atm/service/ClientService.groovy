package ru.otus.atm.service

import ru.otus.atm.dto.ClientAccountsDto

interface ClientService {

    ClientAccountsDto getClient(Integer cardNumber, Integer pin)

    ClientAccountsDto withdrawalCash(Long amount, String currency, ClientAccountsDto dto)

    ClientAccountsDto acceptanceCash(String currency, ClientAccountsDto dto)

}