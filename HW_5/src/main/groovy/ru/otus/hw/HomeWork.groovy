package ru.otus.hw

import org.flywaydb.core.Flyway
import org.slf4j.Logger
import ru.otus.hw.datasource.DriverManagerDataSource
import ru.otus.hw.mapper.EntityClassMetaDataImpl
import ru.otus.hw.mapper.EntitySQLMetaDataImpl
import ru.otus.hw.model.Client
import ru.otus.hw.model.Manager
import ru.otus.hw.repository.DataTemplateJdbc
import ru.otus.hw.repository.DbExecutorImpl
import ru.otus.hw.service.DbServiceClientImpl
import ru.otus.hw.service.DbServiceManagerImpl
import ru.otus.hw.sessionmanager.TransactionRunnerJdbc

import javax.sql.DataSource

import static org.slf4j.LoggerFactory.getLogger

final URL = "jdbc:postgresql://localhost:5432/demoDB"
final USER = "postgres"
final PASSWORD = "wermut00"

final Logger log = getLogger(HomeWork.class)

// Общая часть
def dataSource = new DriverManagerDataSource(URL, USER, PASSWORD)
flywayMigrations(dataSource)
def transactionRunner = new TransactionRunnerJdbc(dataSource)
def dbExecutor = new DbExecutorImpl()

// Работа с клиентом
def entityClassMetaDataClient = new EntityClassMetaDataImpl<>(Client.class)
def entitySQLMetaDataClient = new EntitySQLMetaDataImpl<>(entityClassMetaDataClient)
def dataTemplateClient = new DataTemplateJdbc(
        dbExecutor: dbExecutor,
        entitySQLMetaData: entitySQLMetaDataClient,
        entityClassMetaData: entityClassMetaDataClient) //реализация DataTemplate, универсальная

// Код дальше должен остаться
def dbServiceClient = new DbServiceClientImpl(transactionRunner, dataTemplateClient)
def clientAfterSave = dbServiceClient.saveClient(new Client("dbServiceFirst"))
log.info(">>>after save::{}", clientAfterSave)
//println(" >>>after save: " + clientAfterSave)
def clientSecond = dbServiceClient.saveClient(new Client("dbServiceSecond"))
def clientSecondSelected = dbServiceClient.getClient(clientSecond.getId())
        ?.orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
log.info("clientSecondSelected:{}", clientSecondSelected)
//println(" clientSecondSelected: " + clientSecondSelected)
log.info(">>>All clients::{}", dbServiceClient.findAll())
//println(" >>>All clients: " + dbServiceClient.findAll())
def clientForUpdate = clientSecondSelected
clientForUpdate?.setName("New name for client")
def clientAfterUpdate = dbServiceClient.saveClient(clientForUpdate)

log.info(">>>Last client after update::{}", clientAfterUpdate)
//println(" >>>Last client after update: " + clientAfterUpdate)


// Сделайте тоже самое с классом Manager (для него надо сделать свою таблицу)

def entityClassMetaDataManager = new EntityClassMetaDataImpl<>(Manager.class)
def entitySQLMetaDataManager = new EntitySQLMetaDataImpl<>(entityClassMetaDataManager)
def dataTemplateManager = new DataTemplateJdbc(
        dbExecutor: dbExecutor,
        entitySQLMetaData: entitySQLMetaDataManager,
        entityClassMetaData: entityClassMetaDataManager)

def dbServiceManager = new DbServiceManagerImpl(transactionRunner, dataTemplateManager)
def managerAfterSave = dbServiceManager.saveManager(new Manager("ManagerFirst"))
log.info(">>>after save::{}", managerAfterSave)
//println(" >>>after save: " + managerAfterSave)

def managerSecond = dbServiceManager.saveManager(new Manager("ManagerSecond"))
def managerSecondSelected = dbServiceManager.getManager(managerSecond.getNo())
        .orElseThrow(() -> new RuntimeException("Manager not found, id:" + managerSecond.getNo()))
log.info("managerSecondSelected:{}", managerSecondSelected)
log.info(">>>All managers::{}", dbServiceManager.findAll())
//println(" managerSecondSelected: " + managerSecondSelected)
//println(" >>>All managers: " + dbServiceManager.findAll())

def managerForUpdate = managerSecondSelected
managerForUpdate.setLabel("New Label for manager")
managerForUpdate.setParam1("New param for manager")
def managerAfterUpdate = dbServiceManager.saveManager(managerForUpdate)
log.info(">>>Last manager after update::{}", managerAfterUpdate)
//println(" >>>Last manager after update: " + managerAfterUpdate)
//println("Все!!!!------------------------>")
log.info("-------------------------- БАБУШКА ПРИЕХАЛА!!!! ------------------------------")
private static void flywayMigrations(DataSource dataSource) {
    def logger = getLogger(HomeWork.class)
    logger.info("db migration started...")
    def flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:/db/migration")
            .load()
    flyway.migrate()
    logger.info("db migration finished.")
    logger.info("***")
}
