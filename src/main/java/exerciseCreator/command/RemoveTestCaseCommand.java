package exerciseCreator.command;


import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;

public class RemoveTestCaseCommand implements Command {

    private final TestCase testCaseToRemove;
    private final Task task;

    public RemoveTestCaseCommand(TestCase testCaseToRemove, Task task) {
        this.testCaseToRemove = testCaseToRemove;
        this.task = task;
    }

    @Override
    public void execute() {
        task.removeTestCase(testCaseToRemove);

    }

    @Override
    public void undo() {
        task.addTestCase(testCaseToRemove);

    }

    @Override
    public void redo() {
        task.removeTestCase(testCaseToRemove);
    }

    @Override
    public String getName() {
        return "Removed testCase: " + testCaseToRemove.toString();
    }
}
