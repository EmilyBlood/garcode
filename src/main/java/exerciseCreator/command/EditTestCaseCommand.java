package exerciseCreator.command;


import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;

public class EditTestCaseCommand implements Command {

    private TestCase testCaseToEdit;
    private Task task;

    public EditTestCaseCommand(TestCase testCaseToEdit, Task task) {
        this.testCaseToEdit = testCaseToEdit;
        this.task = task;
    }

    @Override
    public void execute(){
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public String getName() {
        return "Edited testCase: " + testCaseToEdit.toString();
    }
}
