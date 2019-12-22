package ua.nure.kn.momont.usermanagement.db;


public class DataBaseException extends Exception {

	public DataBaseException(Exception e) {
		super(e);
	}

	public DataBaseException(String string) {
		super(string);
	}


}
