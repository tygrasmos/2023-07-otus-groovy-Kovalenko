package ru.otus.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.otus.model.Manager
import ru.otus.repository.DataTemplate
import ru.otus.sessionmanager.TransactionRunner
import ru.otus.repository.DataTemplate
import ru.otus.sessionmanager.TransactionRunner

class DbServiceManagerImpl implements DBServiceManager {
    private static final Logger log = LoggerFactory.getLogger(DbServiceManagerImpl.class)

    private final DataTemplate managerDataTemplate
    private final TransactionRunner transactionRunner

    DbServiceManagerImpl(TransactionRunner transactionRunner, DataTemplate managerDataTemplate) {
        this.transactionRunner = transactionRunner
        this.managerDataTemplate = managerDataTemplate
    }

    @Override
    Manager saveManager(Manager manager) {
        transactionRunner.doInTransaction(connection -> {
            if (manager.getNo() == null) {
                def managerNo = managerDataTemplate.insert(connection, manager)
                def createdManager = new Manager(managerNo, manager.getLabel(), manager.getParam1())
                log.info("created manager: {}", createdManager)
                return createdManager
            }
            managerDataTemplate.update(connection, manager)
            log.info("updated manager: {}", manager)
            manager
        }) as Manager
    }

    @Override
    Optional<Manager> getManager(long no) {
        transactionRunner.doInTransaction(connection -> {
            def managerOptional = managerDataTemplate.findById(connection, no)
            log.info("manager: {}", managerOptional)
            managerOptional
        }) as Optional<Manager>
    }

    @Override
    List<Manager> findAll() {
        transactionRunner.doInTransaction(connection -> {
            def managerList = managerDataTemplate.findAll(connection)
            log.info("managerList:{}", managerList)
            managerList;
        }) as List<Manager>
    }
}
