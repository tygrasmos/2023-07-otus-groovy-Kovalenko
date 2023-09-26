package ru.otus.atm.service

import ru.otus.atm.entity.Banknote
import ru.otus.atm.entity.CashBox

interface ProcessingService {

    List<Banknote> getBanknotes(Long amount, String currencyIdent)

    Long addBanknotes(String currencyIdent, List<Banknote> banknotes)
}
