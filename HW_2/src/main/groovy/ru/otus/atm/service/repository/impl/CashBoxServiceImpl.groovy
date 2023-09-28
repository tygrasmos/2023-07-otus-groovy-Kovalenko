package ru.otus.atm.service.repository.impl

import org.springframework.stereotype.Service
import ru.otus.atm.entity.CashBox
import ru.otus.atm.entity.Currency
import ru.otus.atm.repository.CashBoxRepository
import ru.otus.atm.service.repository.CashBoxService

@Service
class CashBoxServiceImpl implements CashBoxService{


    private final CashBoxRepository cashBoxRepository

    CashBoxServiceImpl(CashBoxRepository cashBoxRepository){
        this.cashBoxRepository = cashBoxRepository
    }

    @Override
    List<CashBox> findAll() {
        return cashBoxRepository.findAll()
    }

    @Override
    List<CashBox> findByCurrency(Currency currency) {
        return cashBoxRepository.findCashBoxesByCurrency()
    }

    @Override
    CashBox findById(Long id) {
        return cashBoxRepository.findById(id).get()
    }

    @Override
    CashBox modifyOrAdd(CashBox cashBox) {
        return cashBoxRepository.save(cashBox)
    }

    @Override
    List<CashBox> modifyOrAddAll(List<CashBox> cashBoxes) {
        return cashBoxRepository.saveAll(cashBoxes)
    }
/*  @Override
    def delete(CashBox cashBox) {
        return cashBoxRepository.delete(cashBox)
    }*/
}
