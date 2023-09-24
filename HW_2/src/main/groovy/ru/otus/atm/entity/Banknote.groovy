package ru.otus.atm.entity

import groovy.transform.CompileStatic
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "banknote")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
class Banknote {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    Long id

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_id")
    Currency currency

    @ManyToOne(targetEntity = Denomination.class)
    @JoinColumn(name = "denomination_id")
    Denomination denomination

}
