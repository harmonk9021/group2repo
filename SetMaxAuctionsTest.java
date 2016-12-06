import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for set max auction user story.
 * User story: As a staff person for Auction Central,
 *   I want to change the maximum number of future auctions 
 *   that the system is allowed to schedule at any one time.
 * 
 * @author Katie Harmon
 * @version 12/4/16
 */
public class SetMaxAuctionsTest {
	private AuctionCalendar calendar;
	
	@Before
	public void setup() {
		calendar = new AuctionCalendar(new AuctionDate(), "Tests.ser");
	}
	
	@Test
	public void testSetMaxAuctionsOnSetMaxToNegative(){
		assertFalse(calendar.setMaxAuctions(-1));
	}
	
	@Test
	public void testSetMaxAuctionsOnSetMaxToZero(){
		assertFalse(calendar.setMaxAuctions(0));
	}
	
	@Test
	public void testSetMaxAuctionsOnSetMaxToOne(){
		assertTrue(calendar.setMaxAuctions(1));
	}
	
	@Test
	public void testSetMaxAuctionsOnSetMaxAboveOne(){
		assertTrue(calendar.setMaxAuctions(25));
	}
}
