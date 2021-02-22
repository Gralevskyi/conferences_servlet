package com.hralievskyi.conferences.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.hralievskyi.conferences.exception.DBException;

public interface GenericDao<T> extends AutoCloseable {
	void create(T entity) throws DBException;

	T findById(long id);

	List<T> findAll();

	void update(T entity) throws DBException;

	void delete(int id);

	void closeConn();

	void closeRs(ResultSet rs);

	void closeSt(Statement stmt);

	void closeAll(Statement stmt, ResultSet rs);

	void rollback();

}
