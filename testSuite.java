import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AuctionTest.class,
	AuctionCalendarTest.class,
	AuctionDateTest.class,
	ItemTest.class,
	UserTest.class,
	LoginTest.class,
	AddAuctionTest.class,
	AddBidTest.class,
	AddItemTest.class,
	RemoveAuctionTest.class,
	RemoveBidTest.class,
	RemoveItemTest.class,
//	ViewAuctionsTest.class, //NOTE:does not hold any tests
	SetMaxAuctionsTest.class
})

public class testSuite {

}
