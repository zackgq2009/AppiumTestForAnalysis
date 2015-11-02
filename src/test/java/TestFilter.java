import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

/**
 * Created by johnny on 15/8/7.
 */
public class TestFilter extends Filter {

    private String methodName = "tenthUserTest";

    public TestFilter(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean shouldRun(Description description) {
        return false;
    }

    @Override
    public String describe() {
        return "This TestFilter just work for me to test some testcases of someone teseclass";
    }
}

