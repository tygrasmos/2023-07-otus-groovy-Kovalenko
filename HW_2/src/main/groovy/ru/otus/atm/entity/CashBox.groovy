package ru.otus.atm.entity

import groovy.transform.Canonical
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Canonical
@Entity
@Table(name = "cash_box")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
class CashBox {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    Long id

    @OneToOne(targetEntity = Banknote.class)
    @JoinColumn(name = "banknote_id")
    Banknote banknote

    @Column(name = "banknote_quantity")
    Integer quantity

    CashBox(Long id, Banknote banknote, Integer quantity){
        this.id = id
        this.banknote = banknote
        this.quantity = quantity
    }

    CashBox() {

    }

    CashBox plus(Integer value){
        new CashBox(this.id, this.banknote, this.quantity + value)
    }

    CashBox minus(Integer value){
        new CashBox(this.id, this.banknote, this.quantity - value)
    }
}
















