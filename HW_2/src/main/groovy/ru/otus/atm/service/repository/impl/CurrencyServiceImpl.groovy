package ru.otus.atm.service.repository.impl

import ru.otus.atm.entity.Currency
import ru.otus.atm.repository.CurrencyRepository
import ru.otus.atm.service.repository.CurrencyService

class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository currencyRepository

    CurrencyServiceImpl(CurrencyRepository currencyRepository){
        this.currencyRepository = currencyRepository
    }

    @Override
    List<Currency> findAll() {
        return currencyRepository.findAll()
    }

    @Override
    Currency findByCurrencyIdent(String currencyIdent) {
        return currencyRepository.findCurrencyByCurrencyIdent()
    }

    @Override
    Currency findById(Long id) {
        return currencyRepository.findById(id).get()
    }

    @Override
    Currency modifyOrAdd(Currency currency) {
        return currencyRepository.save(currency)
    }

  /*  @Override
    def delete(Currency currency) {
        return currencyRepository.delete(currency)
    }*/
}
