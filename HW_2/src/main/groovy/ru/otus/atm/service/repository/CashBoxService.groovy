package ru.otus.atm.service.repository

import ru.otus.atm.entity.CashBox
import ru.otus.atm.entity.Currency

interface CashBoxService {

    List<CashBox> findAll()

    List<CashBox> findByCurrency(Currency currency)

    CashBox findById(Long id)

    CashBox modifyOrAdd(CashBox cashBox)

    List<CashBox> modifyOrAddAll(List<CashBox> cashBoxes)

}
