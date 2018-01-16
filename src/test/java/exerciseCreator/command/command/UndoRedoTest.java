package exerciseCreator.command.command;

import exerciseCreator.command.TestCaseCommand.AddTestCaseCommand;
import exerciseCreator.command.TestCaseCommand.CommandRegistry;
import exerciseCreator.command.TestCaseCommand.EditTestCaseCommand;
import exerciseCreator.command.TestCaseCommand.RemoveTestCaseCommand;
import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoRedoTest {

//    CommandRegistry commandRegistry = new CommandRegistry();
//    Task task = new Task();
//    TestCase testCase = new TestCase("1","1","1");
//    AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);
//    RemoveTestCaseCommand removeTestCaseCommand = new RemoveTestCaseCommand(testCase, task);

    @Test
    public void addTestCaseCommandTOCommandRegistryTest(){

        CommandRegistry commandRegistry = new CommandRegistry();
        Task task = new Task();
        TestCase testCase = new TestCase("1","1","1");
        AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);

        commandRegistry.executeCommand(addTestCaseCommand);
        assertEquals(1, commandRegistry.getCommandStack().size(), "there must be 1 element in command stack");
        assertEquals(0, commandRegistry.getUndoCommandStack().size(), "there must be 0 elements in command stack");
        assertEquals(addTestCaseCommand, commandRegistry.getCommandStack().get(0), "the element should be the same");

    }

    @Test
    public void removeTestCaseCommandTOCommandRegistryTest(){

        CommandRegistry commandRegistry = new CommandRegistry();
        Task task = new Task();
        TestCase testCase = new TestCase("1","1","1");
        AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);
        RemoveTestCaseCommand removeTestCaseCommand = new RemoveTestCaseCommand(testCase, task);

        commandRegistry.executeCommand(addTestCaseCommand);
        commandRegistry.executeCommand(removeTestCaseCommand);
        assertEquals(2, commandRegistry.getCommandStack().size(), "there must be 2 elements in command stack");
        assertEquals(0, commandRegistry.getUndoCommandStack().size(), "there must be 0 element in undo command stack");
        assertEquals(removeTestCaseCommand, commandRegistry.getCommandStack().get(1), "the element should be the same");

    }

    @Test
    public void editTestCaseCommandTOCommandRegistryTest(){

        CommandRegistry commandRegistry = new CommandRegistry();
        Task task = new Task();
        TestCase testCase = new TestCase("1","1","1");
        AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);
        EditTestCaseCommand editTestCaseCommand = new EditTestCaseCommand(testCase, task);

        commandRegistry.executeCommand(addTestCaseCommand);
        commandRegistry.executeCommand(editTestCaseCommand);
        assertEquals(2, commandRegistry.getCommandStack().size(), "there must be 2 elements in command stack");
        assertEquals(0, commandRegistry.getUndoCommandStack().size(), "there must be 0 element in undo command stack");
        assertEquals(editTestCaseCommand, commandRegistry.getCommandStack().get(1), "the element should be the same");

    }

    @Test
    public void undoCommandRegistryTest(){

        CommandRegistry commandRegistry = new CommandRegistry();
        Task task = new Task();
        TestCase testCase = new TestCase("1","1","1");
        AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);

        commandRegistry.executeCommand(addTestCaseCommand);
        commandRegistry.undo();
        assertEquals(0, commandRegistry.getCommandStack().size(), "there must be 0 elements in command stack");
        assertEquals(1, commandRegistry.getUndoCommandStack().size(), "there must be 1 element in undo command stack");
        assertEquals(addTestCaseCommand, commandRegistry.getUndoCommandStack().get(0), "the element should be the same");

    }


    @Test
    public void redoCommandRegistryWhenRegistryEmptyTest(){

        CommandRegistry commandRegistry = new CommandRegistry();
        Task task = new Task();
        TestCase testCase = new TestCase("1","1","1");
       // AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);

       // commandRegistry.executeCommand(addTestCaseCommand);
        commandRegistry.redo();
        assertEquals(0, commandRegistry.getCommandStack().size(), "there must be 0 elements in command stack");
        assertEquals(0, commandRegistry.getUndoCommandStack().size(), "there must be 0 elements in undo command stack");

    }


    @Test
    public void redoCommandRegistryWhenRegistryNotEmptyTest(){

        CommandRegistry commandRegistry = new CommandRegistry();
        Task task = new Task();
        TestCase testCase = new TestCase("1","1","1");
        AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);

        commandRegistry.executeCommand(addTestCaseCommand);
        commandRegistry.undo();
        commandRegistry.redo();
        assertEquals(1, commandRegistry.getCommandStack().size(), "there must be 0 elements in command stack");
        assertEquals(0, commandRegistry.getUndoCommandStack().size(), "there must be 0 elements in undo command stack");

    }


    @Test
    public void redoCommandRegistryWhenRegistryNotEmpty2Test(){

        CommandRegistry commandRegistry = new CommandRegistry();
        Task task = new Task();
        TestCase testCase = new TestCase("1","1","1");
        AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);
        EditTestCaseCommand editTestCaseCommand = new EditTestCaseCommand(testCase, task);
        RemoveTestCaseCommand removeTestCaseCommand = new RemoveTestCaseCommand(testCase, task);

        commandRegistry.executeCommand(addTestCaseCommand);
        commandRegistry.executeCommand(editTestCaseCommand);
        commandRegistry.executeCommand(removeTestCaseCommand);
        commandRegistry.undo();
        commandRegistry.undo();
        commandRegistry.redo();

        assertEquals(2, commandRegistry.getCommandStack().size(), "there must be 0 elements in command stack");
        assertEquals(1, commandRegistry.getUndoCommandStack().size(), "there must be 0 elements in undo command stack");
        assertEquals(removeTestCaseCommand, commandRegistry.getUndoCommandStack().get(0), "the element should be the same");

    }

}
