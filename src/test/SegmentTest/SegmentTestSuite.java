import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by johnny on 15/8/24.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PaymentsUsersTest.class,
        UsageUsersTest.class,
        EventsUsersTest.class
})
public class SegmentTestSuite {
}
