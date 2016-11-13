/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jacob Ackerman
 */
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Database testDB = new Database();
        testDB.forcePopulate(25);
        Auction testAuction = new Auction(new Date(), "Test Auction");
        testAuction.myOrg = "Org5";
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addAuctionToDB method, of class Database.
     */
    @Test
    public void testAddAuctionToDBSunnyDay() {
        System.out.println("addAuctionToDB - \"Sunny Day\" Scenario");
        Auction testAuction = new Auction(new Date(), "Test Auction");
        testAuction.myOrg = "Org35";
        Database instance = new Database();
        instance.forcePopulate(20);
        boolean expResult = true;
        boolean result = instance.addAuctionToDB(testAuction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testAddAuctionToDBOneYear() {
        System.out.println("addAuctionToDB - \"Auction requested in past year\" Scenario");
        
        Auction testAuction = new Auction(new Date(), "Test Auction");
        testAuction.myOrg = "Org45";
        Database instance = new Database();
        instance.forcePopulate(22);
        boolean expResult = false;
        boolean result = instance.addAuctionToDB(testAuction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testAddAuctionToDBTooMany() {
        System.out.println("addAuctionToDB - \"More than 25 auctions\" Scenario");
        Auction testAuction = new Auction(new Date(), "Test Auction");
        testAuction.myOrg = "Org35";
        Database instance = new Database();
        instance.forcePopulate(26);
        boolean expResult = false;
        boolean result = instance.addAuctionToDB(testAuction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testAddAuctionToDBDayTooFull() {
        System.out.println("addAuctionToDB - \"2 or more auctions on one day\" Scenario");
        Date d = new Date();
        Date x = new Date();
        x.setDate(d.getDay()+14);
        Auction testAuction = new Auction(x, "Test Auction");
        testAuction.myOrg = "Org35";
        Database instance = new Database();
        instance.forcePopulate(20);
        boolean expResult = false;
        boolean result = instance.addAuctionToDB(testAuction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAuction method, of class Database.
     */
    @Test
    public void testGetAuction() {
        System.out.println("getAuction");
        Date key = null;
        Database instance = new Database();
        Auction expResult = null;
        Auction result = instance.getAuction(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuctionList method, of class Database.
     */
    @Test
    public void testGetAuctionList() {
        System.out.println("getAuctionList");
        Database instance = new Database();
        List<Auction> expResult = null;
        List<Auction> result = instance.getAuctionList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
    }

    /**
     * Test of addUserToDB method, of class Database.
     */
    @Test
    public void testAddUserToDB() {
        System.out.println("addUserToDB");
        User theUser = null;
        Database instance = new Database();
        instance.addUserToDB(theUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class Database.
     */
    /*
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        String key = "";
        Database instance = new Database();
        User expResult = null;
        User result = instance.getUser(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of getUserList method, of class Database.
     */
    /*
    @Test
    public void testGetUserList() {
        System.out.println("getUserList");
        Database instance = new Database();
        List<User> expResult = null;
        List<User> result = instance.getUserList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of Update method, of class Database.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update");
    //    String auctionFileName = "";
    //    String userFileName = "";
    //    Database instance = new Database();
    //    boolean expResult = false;
     //   boolean result = instance.Update(auctionFileName, userFileName);
   //     assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Load method, of class Database.
     */
    @Test
    public void testLoad() {
    //    System.out.println("Load");
    //    String auctionFileName = "";
    //    String userFileName = "";
    //    Database instance = new Database();
    //    boolean expResult = false;
    //    boolean result = instance.Load(auctionFileName, userFileName);
    //    assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
