package com.hralievskyi.conferences.dao.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;

public class ConnectionPoolHolder {

	private static final Logger LOG = Logger.getLogger(ConnectionPoolHolder.class);

	private static volatile DataSource dataSource;

	public static DataSource getDataSource() throws DBException {
		LOG.debug("start with dataSource " + dataSource);
		if (dataSource == null) {
			synchronized (ConnectionPoolHolder.class) {
				if (dataSource == null) {
					try {
						Context initContext = new InitialContext();
						Context envContext = (Context) initContext.lookup("java:/comp/env");
						// conference - the name of data source
						dataSource = (DataSource) envContext.lookup("jdbc/conferencedb");
						LOG.trace("Data source ==> " + dataSource);
					} catch (NamingException ex) {
						LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
						throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
					}
				}
			}
		}
		LOG.debug(" return dataSource " + dataSource);
		return dataSource;

	}

}
