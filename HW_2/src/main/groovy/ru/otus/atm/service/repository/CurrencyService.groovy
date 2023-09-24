package ru.otus.atm.service.repository

import ru.otus.atm.entity.Currency

interface CurrencyService {

    List<Currency> findAll()

    Currency findByCurrencyIdent(String currencyIdent)

    Currency findById(Long id)

    Currency modifyOrAdd(Currency currency)

}