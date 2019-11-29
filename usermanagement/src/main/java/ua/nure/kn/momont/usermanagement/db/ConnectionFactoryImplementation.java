package ua.nure.kn.momont.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImplementation implements ConnectionFactory {

	private String driver = "org.hsqldb.jdbcDriver";
	private String url = "jdbc:hsqldb:file:db/usermanagement";
	private String user = "sa";
	private String password = "";

	public ConnectionFactoryImplementation(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public ConnectionFactoryImplementation(Properties properties) {
		user = properties.getProperty("connection.user");
		password = properties.getProperty("connection.password");
		url = properties.getProperty("connection.url");
		driver = properties.getProperty("connection.driver");
	}

	@Override
	public Connection createConnection() throws DataBaseException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
	}

}
