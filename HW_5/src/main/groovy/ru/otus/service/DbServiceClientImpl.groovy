package ru.otus.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.otus.model.Client
import ru.otus.repository.DataTemplate
import ru.otus.sessionmanager.TransactionRunner
import ru.otus.repository.DataTemplate
import ru.otus.sessionmanager.TransactionRunner


class DbServiceClientImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class)

    private final DataTemplate dataTemplate
    private final TransactionRunner transactionRunner

    DbServiceClientImpl(TransactionRunner transactionRunner, DataTemplate dataTemplate) {
        this.transactionRunner = transactionRunner
        this.dataTemplate = dataTemplate
    }

    @Override
    Client saveClient(Client client) {
        transactionRunner.doInTransaction(connection -> {
            if (client.getId() == null) {
                def clientId = dataTemplate.insert(connection, client)
                def createdClient = new Client(clientId, client.getName())
                log.info("created client: {}", createdClient)
                return createdClient
            }
            dataTemplate.update(connection, client)
            log.info("updated client: {}", client)
            client
        }) as Client
    }

    @Override
    Optional<Client> getClient(long id) {
        transactionRunner.doInTransaction(connection -> {
            def clientOptional = dataTemplate.findById(connection, id)
            log.info("client: {}", clientOptional)
            clientOptional
        }) as Optional<Client>
    }

    @Override
    List<Client> findAll() {
        transactionRunner.doInTransaction(connection -> {
            def clientList = dataTemplate.findAll(connection)
            log.info("clientList:{}", clientList)
            clientList
        }) as List<Client>
    }
}
