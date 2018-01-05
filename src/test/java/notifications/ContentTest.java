package notifications;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {
    String taskName = "Task Name";
    String taskResult = "Test Result";
    Content content = new Content(taskName, taskResult);

    @Test
    void getTaskName() {
        assertEquals(taskName, content.getTaskName());
    }

    @Test
    void setTaskName() {
        String newTaskName = "New Task Name";
        content.setTaskName(newTaskName);
        assertEquals(newTaskName, content.getTaskName());
    }

    @Test
    void getTestResult() {
        assertEquals(taskResult, content.getTestResult());
    }

    @Test
    void setTestResult() {
        String newTestResult = "New Test Result";
        content.setTestResult("New Test Result");
        assertEquals(newTestResult, content.getTestResult());
    }

}