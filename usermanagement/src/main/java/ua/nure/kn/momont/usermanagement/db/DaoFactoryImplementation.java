package ua.nure.kn.momont.usermanagement.db;


public class DaoFactoryImplementation extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		UserDao result = null;
	    try {
	        Class clazz = Class.forName(properties.getProperty(USER_DAO));
	        result = (UserDao) clazz.newInstance();
	        result.setConnectionFactory(getConnectionFactory());
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    return result;
	}

}
