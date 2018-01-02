//package notifications;
//
//import exerciseCreator.Outcome;
//import org.junit.jupiter.api.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ContentTest {
//    @Test
//    void getParticipantEmail() {
//        String address = "test@test.com";
//        Outcome outcome = new Outcome(10, "Adam", "Adamiak", "blaszkiewiczmilosz@interia.eu", "Excellent")
//        Content content = new Content(address, "Task Name One", "Test Result One");
//        assertEquals(address, content.getParticipantEmail());
//    }
//
//    @Test
//    void setParticipantEmail() {
//        String address = "test123@test.com";
//        Content content = new Content("test@test.com", "Task Name One", "Test Result One");
//        content.setParticipantEmail(address);
//        assertEquals(address, content.getParticipantEmail());
//    }
//
//    @Test
//    void getTaskName() {
//        String newTaskName = "New Task Name";
//        Content content = new Content("test@test.com", newTaskName, "Test Result One");
//        assertEquals(newTaskName, content.getTaskName());
//    }
//
//    @Test
//    void setTaskName() {
//        String newTaskName = "New Task Name";
//        Content content = new Content("test@test.com", "Old Task Name", "Test Result One");
//        content.setTaskName(newTaskName);
//        assertEquals(newTaskName, content.getTaskName());
//    }
//
//    @Test
//    void getTestResult() {
//        String newTestResult = "New Test Result";
//        Content content = new Content("test@test.com", "Task Name One", newTestResult);
//        assertEquals(newTestResult, content.getTestResult());
//    }
//
//    @Test
//    void setTestResult() {
//        String newTestResult = "New Test Result";
//        Content content = new Content("test@test.com", "Task Name One", "Old Test Result");
//        content.setTestResult("New Test Result");
//        assertEquals(newTestResult, content.getTestResult());
//    }
//
//}