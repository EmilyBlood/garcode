package exerciseCreator.command.TestCaseCommand;

public interface Command {

	void execute();

	void undo();

	void redo();

	String getName();
}
