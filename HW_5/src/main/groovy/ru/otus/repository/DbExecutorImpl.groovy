package ru.otus.repository

import ru.otus.exception.DataBaseOperationException

import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class DbExecutorImpl implements DbExecutor {

    @Override
    long executeStatement(Connection connection, String sql, List<Object> params) {
        try (def pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (def idx = 0; idx < params.size(); idx++) {
                def curParam = params.get(idx)
                if (curParam == null && idx == 0) {
                    pst.setObject(idx + 1, getNextValue(connection))
                } else {
                    pst.setObject(idx + 1, curParam)
                }
            }
            pst.executeUpdate()
            try (def rs = pst.getGeneratedKeys()) {
                rs.next()
                return rs.getInt(1)
            }
        } catch (ex) {
            throw new DataBaseOperationException("executeInsert error", ex)
        }
    }

    @Override
    <T> Optional<T> executeSelect(Connection connection, String sql, List<Object> params, Closure<T> rsHandler) {
        try (def pst = connection.prepareStatement(sql)) {
            for (def idx = 0; idx < params.size(); idx++) {
                pst.setObject(idx + 1, params.get(idx))
            }
            try (def rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.call(rs))
            }
        } catch (ex) {
            throw new DataBaseOperationException("executeSelect error", ex)
        }
    }

    static Long getNextValue(Connection connection){
        try (ResultSet pst = connection.createStatement().executeQuery("select nextval('seq')")) {
            if (pst.next()) {
                pst.getLong(1)
            }
        } catch (ex) {
            throw new DataBaseOperationException("sequence_exception", ex)
        }

    }
}
