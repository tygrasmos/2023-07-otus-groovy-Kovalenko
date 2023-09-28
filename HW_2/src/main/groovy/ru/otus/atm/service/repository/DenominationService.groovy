package ru.otus.atm.service.repository

import ru.otus.atm.entity.Denomination

interface DenominationService {

    List<Denomination> findAll()

    Denomination modifyOrAdd(Denomination denomination)

}