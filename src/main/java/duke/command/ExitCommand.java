package duke.command;

import java.util.Map;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public ExitCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
