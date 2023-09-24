package ru.otus.atm.service.repository.impl

import ru.otus.atm.entity.Denomination
import ru.otus.atm.repository.DenominationRepository
import ru.otus.atm.service.repository.DenominationService

class DenominationServiceImpl implements DenominationService{

    private final DenominationRepository denominationRepository

    DenominationServiceImpl(DenominationRepository denominationRepository){
        this.denominationRepository = denominationRepository
    }


    @Override
    List<Denomination> findAll() {
        return denominationRepository.findAll()
    }

    @Override
    Denomination modifyOrAdd(Denomination denomination) {
        return denominationRepository.save(denomination)
    }

   /* @Override
    def delete(Denomination denomination) {
        return denominationRepository.delete(denomination)
    }*/
}
