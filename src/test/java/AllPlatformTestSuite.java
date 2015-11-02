import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by johnny on 15/8/7.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AndroidGameTest.class,
        IosGameTest.class
})

public class AllPlatformTestSuite {
}
