package ru.otus.hw.repository

import java.sql.Connection

interface DbExecutor<T> {

    long executeStatement(Connection connection, String sql, List<Object> params);

    <T> Optional<T> executeSelect(Connection connection, String sql, List<Object> params, Closure<T> rsHandler) ;
}
