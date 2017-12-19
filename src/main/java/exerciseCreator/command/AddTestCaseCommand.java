package exerciseCreator.command;


import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;

public class AddTestCaseCommand implements Command {

    private TestCase testCaseToAdd;
    private Task task;

    public AddTestCaseCommand(TestCase testCaseToAdd, Task task) {
        this.testCaseToAdd = testCaseToAdd;
        this.task = task;
    }

    @Override
    public String getName() {
        return "New testCase: " + testCaseToAdd.toString();
    }

    @Override
    public void execute() {
        task.addTestCase(testCaseToAdd);
    }

    @Override
    public void undo() {
        task.removeTestCase(testCaseToAdd);

    }

    @Override
    public void redo() {
        task.addTestCase(testCaseToAdd);
    }


}
