package ua.nure.kn.momont.usermanagement.db;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn.momont.usermanagement.User;

class HsqldbUserDao implements UserDao{
	
	private static final String SELECT_QUERY = "select * from users where id=?";
	private static final String DELETE_QUERY = "delete from users where id = ?";
	private static final String UPDATE_QUERY = "update users set firstname = ?, lastname = ?, dateofbirth = ? where id = ?";
	private static final String SELECT_ALL_QUERY = "select id, firstname, lastname, dateofbirth from users";
	private static final String INSERT_QUERY = "insert into users (firstname, lastname, dateofbirth) values (?,?,?)";
	private static final String SELECT_BY_NAMES_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE firstname=? AND lastname=?";
	private ConnectionFactory connectionFactory;
	
	@Override
	public User create(User entity) throws DataBaseException {
		
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = 
					connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			int numberOfRows = preparedStatement.executeUpdate();
            if (numberOfRows != 1){
                throw new DataBaseException("Number of rows: " + numberOfRows);
            }
            CallableStatement callableStatement = connection.prepareCall("call IDENTITY() ");
            ResultSet keys = callableStatement.executeQuery();
            if (keys.next()) {
            	entity.setId(new Long(keys.getLong(1)));
            }
            keys.close();
            connection.close();
            callableStatement.close();
            preparedStatement.close();
            return entity;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		} catch (DataBaseException e) {
			throw e;
		}
	}
	
	@Override
	public void update(User entity) throws DataBaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			preparedStatement.setLong(4, entity.getId());
			int numberOfRows = preparedStatement.executeUpdate();
			if(numberOfRows != 1) {
				throw new DataBaseException("Number of rows: " + numberOfRows);
			}
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DataBaseException(e);
		} catch (DataBaseException e) {
			throw e;
		}
		
	}
	
	@Override
	public void delete(User entity) throws DataBaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setLong(1, entity.getId());
			int numberOfRows = preparedStatement.executeUpdate();
			if(numberOfRows != 1) {
				throw new DataBaseException("Number of rows: " + numberOfRows);
			}
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DataBaseException(e);
		} catch (DataBaseException e) {
			throw e;
		}
	}
	
	@Override
	public User find(Long id) throws DataBaseException {
		User entity = new User();
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				entity.setId(resultSet.getLong(1));
				entity.setFirstName(resultSet.getString(2));
				entity.setLastName(resultSet.getString(3));
				entity.setDateOfBirth(resultSet.getDate(4));
			}
			
			connection.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			throw new DataBaseException(e);
		} catch (DataBaseException e) {
			throw e;
		}
		return entity;
	}

	@Override
	public Collection<User> findAll() throws DataBaseException {
		Collection<User> result = new LinkedList<User>();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
	        statement.close();
	        connection.close();
		} catch (SQLException e) {
			throw new DataBaseException(e);
		} catch (DataBaseException e) {
			throw e;
		}
		return result;
	}
	
	public Collection find(String firstName, String lastName) throws DataBaseException {
		Collection<User> result = new LinkedList<User>();

		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAMES_QUERY);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));

				result.add(user);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (DataBaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
		return result;
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	public HsqldbUserDao() {
		
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

}
