package ru.otus.atm.model

import ru.otus.atm.entity.Currency

class Account {

    Long accountNumber
    Integer cardNumber
    Currency currency
    Long amount
    Long clientId

    Account(Long accountNumber,
            Integer cardNumber,
            Currency currency,
            Long clientId,
            Long amount){
        this.accountNumber = accountNumber
        this.cardNumber = cardNumber
        this.currency = currency
        this.clientId = clientId
        this.amount = amount
    }

    Long getAccountNumber() {
        return accountNumber
    }

    void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber
    }

    Currency getCurrency() {
        return currency
    }

    void setCurrency(Currency currency) {
        this.currency = currency
    }

    Long getAmount() {
        return amount
    }

    void setAmount(Long amount) {
        this.amount = amount
    }

    Integer getCardNumber() {
        return cardNumber
    }

    void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber
    }
}
