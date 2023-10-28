package ru.otus.sessionmanager;

interface TransactionRunner {
    <T> T doInTransaction(Closure<T> action)
}
