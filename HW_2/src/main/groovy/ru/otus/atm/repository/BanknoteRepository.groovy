package ru.otus.atm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import ru.otus.atm.entity.Banknote

interface BanknoteRepository extends JpaRepository<Banknote, Long>, JpaSpecificationExecutor<Banknote> {

}
