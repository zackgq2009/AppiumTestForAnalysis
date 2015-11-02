import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.Arrays;
import java.util.List;

public class FilterRunner extends BlockJUnit4ClassRunner {
    private List<String> testsToRun = Arrays.asList(new String[]{"test1"});

    public FilterRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description= describeChild(method);
        if (method.getAnnotation(Ignore.class) != null || !testsToRun.contains(method.getName())) {
            notifier.fireTestIgnored(description);
        } else {
            runLeaf(methodBlock(method), description, notifier);
        }
    }
}