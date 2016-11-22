import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This Class will handle logging into Auction Central.
 *  
 * @author Jacob Ackerman
 * @editor Katie Harmon
 * @version 11.20.2016.9:43P
*/
public class Login implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	private Map<String, User> myUserList;
	String fileName;
	
	public Login(String fileName){
	//	loadUserInfo(fileName);
		this.fileName = fileName;
	}
	
	/**
     * This function will package up the user and auction hashes into .ser
     * files. Call this whenever you want to save all changes. Will return
     * a boolean based on if the update worked (true) or if there was a
     * problem (false).
     * 
     * @return A true/false value based on if the files saved correctly
     */
	public boolean writeUserInfo(String userFileName)
    {
        boolean didItWork;
        didItWork = (dumpUsersToFile(userFileName));
        return didItWork;
    }
	
	public boolean loadUserInfo(String userFileName)
	    {
	        boolean didItWork;
	        didItWork = (loadUsersFromFile(userFileName));
	        return didItWork;
	    }
	
	
	 /**@ return true if the user was succesfully added to the system
	  * @ return false if the name is already in use
	 */
	 public boolean addUser(User theUser)
	    {
		 	if(isUnusedUserName(theUser.getUserName())){
		 		myUserList.put(theUser.getUserName(), theUser); // placeholder based on assumed getter name
		 		return true;
		 	}
		 	else return false;
	    }
	 
	 /**@Return true if the user name is not in use, false if the username it*/
		private boolean isUnusedUserName(String userName){
			if(!myUserList.containsKey(userName)) return true;
			else return false;
		}
	 
	 /**getter*/
	 public List<User> getUserList()
	    {
	        return new ArrayList<>(myUserList.values());
	    }
	 
	 public User getUser(String key)
	    {
	        return myUserList.get(key);
	    }
	 
	 public boolean isValidPassword(String userName, String password){
			User temp = myUserList.get(userName);
			return temp.authenticate(userName, password);
		}
	 
	 /**helper function for writeUserInfo*/
	 private boolean dumpUsersToFile(String userFileName)
	    {
	        boolean didItWork = false;
	        try
	      {
	         FileOutputStream fileOut = new FileOutputStream(userFileName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(myUserList);
	         out.close();
	         fileOut.close();
	         didItWork = true;
	         //System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      }
	        catch(IOException i)
	      {
	          //return false;
	      }
	        return didItWork;
	    }
	 
	 /**helper function for loadUserInfo*/
	 private boolean loadUsersFromFile(String userFileName)
	    {
	        try
	      {
	         FileInputStream fileIn = new FileInputStream(userFileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         myUserList = (HashMap) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         //i.printStackTrace();
	         return false;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("User class not found");
	         c.printStackTrace();
	         return false;
	      }
	        return true;
	    }
	
}
