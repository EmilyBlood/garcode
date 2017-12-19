package notifications;

/**
 * Created by Michał on 19.12.2017.
 */
public class Content {
    private String participantEmail;
    private String taskName;
    private String testResult;

    public Content(String participantEmail, String taskName, String testResult) {
        this.participantEmail = participantEmail;
        this.taskName = taskName;
        this.testResult = testResult;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
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
