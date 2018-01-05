package notifications;

/**
 * Created by Michał on 19.12.2017.
 */
final public class Content {
    final private String taskName;
    final private String testResult;

    public Content(String taskName, String testResult) {
        this.taskName = taskName;
        this.testResult = testResult;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTestResult() {
        return testResult;
    }
}
