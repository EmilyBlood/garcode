package exerciseCreator.command.TestCaseCommand;

import exerciseCreator.command.TestCaseCommand.Command;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandRegistry {

	private ObservableList<Command> commandStack = FXCollections
			.observableArrayList();

	private ObservableList<Command> undoCommandStack = FXCollections
			.observableArrayList();

	public void executeCommand(Command command) {
		command.execute();
		commandStack.add(command);
	}

	public void redo() {
		if(!undoCommandStack.isEmpty()){
			Command com = undoCommandStack.remove(undoCommandStack.size()-1);
			com.redo();
			commandStack.add(com);

		}
		
	}

	public void undo() {
		if(!commandStack.isEmpty()){
			Command com = commandStack.remove(commandStack.size()-1);
			com.undo();
			undoCommandStack.add(com);
		}
		
	}

	public ObservableList<Command> getCommandStack() {
		return commandStack;
	}
}
