package ru.otus.atm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import ru.otus.atm.entity.CashBox
import ru.otus.atm.entity.Currency

interface CashBoxRepository extends JpaRepository<CashBox, Long>, JpaSpecificationExecutor<CashBox> {

    List<CashBox> findCashBoxesByCurrency(Currency currency)

}
