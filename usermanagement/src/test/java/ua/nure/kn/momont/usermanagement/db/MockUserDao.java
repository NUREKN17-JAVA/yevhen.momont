package ua.nure.kn.momont.usermanagement.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.kn.momont.usermanagement.User;

public class MockUserDao implements UserDao {

	private long id = 0;
	private Map users = new HashMap();
		
	public User create(User user) throws DataBaseException {
		Long currentId = new Long(++id);
		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	public void update(User user) throws DataBaseException {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);
	}

	@Override
	public void delete(User user) throws DataBaseException {
		Long currentId = user.getId();
		users.remove(currentId);

	}

	@Override
	public User find(Long id) throws DataBaseException {
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DataBaseException {
		return users.values();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}
