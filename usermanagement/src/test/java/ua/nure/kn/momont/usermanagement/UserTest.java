package ua.nure.kn.momont.usermanagement;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	private User user;
	
	public static final String First_Name = "Yevhen";
    public static final String Last_Name = "Momont";
    public static final String Etalon_Full_Name = "Momont, Yevhen";
    
    public static final int YEAR_OF_BIRTH = 2000;
    public static final int MONTH_OF_BIRTH = Calendar.OCTOBER;
    public static final int DATE_OF_BIRTH = 10;
    public static final int ETALON_AGE = 19;
    
    
    public static final int YEAR_OF_BIRTH_TODAY = 1999;
    public static final int MONTH_OF_BIRTH_TODAY = Calendar.OCTOBER;
    public static final int DATE_OF_BIRTH_TODAY = 10;
    public static final int ETALON_AGE_TODAY = 19;
	
    public static final int YEAR_OF_BIRTH_TODAY_NEXT_DAY = 1999;
    public static final int MONTH_OF_BIRTH_TODAY_NEXT_DAY = Calendar.OCTOBER;
    public static final int DATE_OF_BIRTH_TODAY_NEXT_DAY = 11;
    public static final int ETALON_AGE_TODAY_NEXT_DAY = 19;
    
    public static final int YEAR_OF_BIRTH_TODAY_NEXT_MONTH = 1999;
    public static final int MONTH_OF_BIRTH_TODAY_NEXT_MONTH = Calendar.NOVEMBER;
    public static final int DATE_OF_BIRTH_TODAY_NEXT_MONTH = 10;
    public static final int ETALON_AGE_TODAY_NEXT_MONTH = 19;
    
	public void testGetFullName() {
		user.setFirstName(First_Name);
		user.setLastName(Last_Name);
		assertEquals(Etalon_Full_Name, user.getFullName());
	}
	
	public void testGetAgeInTheFuture() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DATE_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALON_AGE, user.getAge());
	}
	
	public void testGetAgeToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH_TODAY, MONTH_OF_BIRTH_TODAY, DATE_OF_BIRTH_TODAY);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALON_AGE_TODAY, user.getAge());
	}
	
	public void testGetAgeNextDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH_TODAY_NEXT_DAY, MONTH_OF_BIRTH_TODAY_NEXT_DAY, DATE_OF_BIRTH_TODAY_NEXT_DAY);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALON_AGE_TODAY_NEXT_DAY, user.getAge());
	}
	
	public void testGetAgeNextMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH_TODAY_NEXT_MONTH, MONTH_OF_BIRTH_TODAY_NEXT_MONTH, DATE_OF_BIRTH_TODAY_NEXT_MONTH);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETALON_AGE_TODAY_NEXT_DAY, user.getAge());
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}
	
	
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
