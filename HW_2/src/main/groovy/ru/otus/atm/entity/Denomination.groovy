package ru.otus.atm.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "denomination")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
class Denomination {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    Long id

    @Column(name = "denomination")
    Integer denomination

    Denomination(Long id, Integer denomination) {
        this.id = id
        this.denomination = denomination
    }

    Denomination() {

    }
}
