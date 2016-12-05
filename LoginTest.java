import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**	This class Tests the methods found in the Login class
 * 
 * @author Katie Harmon
 * @version 11-20-2016
 */
public class LoginTest {
	Login thisLogin;
	User user1;
	
	
	@Before
	public void setUp(){
		thisLogin = new Login("loginTest.ser");
	//	thisLogin.loadUserInfo(userFileName)
		user1 = new User();
		
		user1.setUserName("Steven");
		user1.setPassword("Awesome");
	}
	
	
	@Test
	public void testAddUserOnUserNameDoesNotExist() {
		assertTrue(thisLogin.addUser(user1));
	}
	
	@Test
	public void testAddUserOnUserNameDoesExist() {
		assertTrue(thisLogin.addUser(user1));
		assertFalse(thisLogin.addUser(user1));
		
	}
	
	@Test
	public void testIsValidPasswordOnNotValidPassword() {
		thisLogin.addUser(user1);
		assertTrue(thisLogin.isValidPassword(user1.getUserName(), user1.getPassword()));
	}
	
	@Test
	public void testIsValidPasswordOnValidPassword() {
		thisLogin.addUser(user1);
		assertFalse(thisLogin.isValidPassword(user1.getUserName(), "Not the password"));
	}

}
