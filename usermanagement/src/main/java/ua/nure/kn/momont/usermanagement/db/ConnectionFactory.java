package ua.nure.kn.momont.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DataBaseException;
	
}
