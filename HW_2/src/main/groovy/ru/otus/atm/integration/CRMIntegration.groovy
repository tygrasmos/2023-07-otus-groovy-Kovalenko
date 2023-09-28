package ru.otus.atm.integration

import ru.otus.atm.model.Client

interface CRMIntegration {

    Client getClient(Integer cardNumber, Integer pin)

}
