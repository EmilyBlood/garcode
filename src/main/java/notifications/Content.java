package notifications;

/**
 * Created by Michał on 19.12.2017.
 */
public class Content {
    private String taskName;
    private String testResult;

    public Content(String taskName, String testResult) {
        this.taskName = taskName;
        this.testResult = testResult;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
