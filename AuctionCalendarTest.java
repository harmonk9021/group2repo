/**
 * Test cases for AuctionCalendar class.
 * 
 * @author Cody Arnold
 * @version 11/19/16
 */


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AuctionCalendarTest {

	private AuctionDate date;
	private Auction auction;
	private AuctionCalendar calendar;
	
	@Before
	public void setup() {
		date = new AuctionDate();
		auction = new Auction(date, "KKK");
		calendar = new AuctionCalendar(date);
		AuctionCalendar calendar = new AuctionCalendar(date);
	}
	
	@Test
	public void testAddAuction() {
		assertTrue(calendar.addAuction(auction));
	}
	
	@Test
	public void testGetAuctions() {
		assertEquals(calendar.getAuctions().size(), 0);
	}
}

