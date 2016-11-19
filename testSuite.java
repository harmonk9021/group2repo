import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AuctionTest.class,
	AuctionCalendarTest.class,
	DatabaseTest.class,
	ItemTest.class,
	UserTest.class
})

public class testSuite {

}
