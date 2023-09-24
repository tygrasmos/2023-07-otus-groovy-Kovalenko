package ru.otus.atm.service.repository.impl

import ru.otus.atm.entity.Banknote
import ru.otus.atm.repository.BanknoteRepository
import ru.otus.atm.service.repository.BanknoteService

class BanknoteServiceImpl implements BanknoteService{

    private final BanknoteRepository banknoteRepository

    BanknoteServiceImpl(BanknoteRepository banknoteRepository){
        this.banknoteRepository = banknoteRepository
    }

    @Override
    List<Banknote> findAll() {
        return banknoteRepository.findAll()
    }

    @Override
    Banknote modifyOrAdd(Banknote banknote) {
        return banknoteRepository.save(banknote)
    }

  /*  @Override
    def delete(Banknote banknote) {
        return banknoteRepository.delete(banknote)
    }*/
}
