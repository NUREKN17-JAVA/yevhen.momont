package ua.nure.kn.momont.usermanagement.agent;

public class SearchException extends Exception{
	 private String name;

	    public SearchException(Exception e) {
	        this.name = e.toString();
	    }

	    public String getName() { return name; }

	    @Override
	    public String getMessage() {
	        return super.getMessage();
	    }
}
