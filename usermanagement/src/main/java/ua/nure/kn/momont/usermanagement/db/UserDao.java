package ua.nure.kn.momont.usermanagement.db;

import java.util.Collection;

import ua.nure.kn.momont.usermanagement.User;

public interface UserDao{
    /*T create(T entity) throws DataBaseException;

    void update(T entity) throws DataBaseException;

    void delete(T entity) throws DataBaseException;

    T find(Long id) throws DataBaseException;

    Collection<T> findAll() throws DataBaseException;
    
    void setConnectionFactory(ConnectionFactory connectionFactory);*/
	User create(User user) throws DataBaseException;
	
	void update(User user) throws DataBaseException;
	
	void delete(User user) throws DataBaseException;
	
	User find(Long id) throws DataBaseException;
	
	Collection findAll() throws DataBaseException;
	
	void setConnectionFactory(ConnectionFactory connectionFactory);
}