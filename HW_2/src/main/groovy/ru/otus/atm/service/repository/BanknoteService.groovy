package ru.otus.atm.service.repository

import ru.otus.atm.entity.Banknote

interface BanknoteService {

    List<Banknote> findAll()

    Banknote modifyOrAdd(Banknote banknote)

}