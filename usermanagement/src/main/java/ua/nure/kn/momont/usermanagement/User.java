package ua.nure.kn.momont.usermanagement;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(dateOfBirth, user.dateOfBirth);
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth);
    }	
	
	public String getFullName() {
		return getLastName() + ", " + getFirstName();
	}
	
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.setTime(getDateOfBirth());
    	int yearOfBirth = calendar.get(Calendar.YEAR);
    	int monthOfBirth = calendar.get(Calendar.MONTH);
		int dayOfBirth = calendar.get(Calendar.DAY_OF_MONTH);
		
		if (currentMonth > monthOfBirth) || (currentMonth == monthOfBirth )
		
    	if (yearOfBirth == 1999) {
			return currentYear - yearOfBirth - 1;
		}
		
		return currentYear - yearOfBirth;
    }
}
