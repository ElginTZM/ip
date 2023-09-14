package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {

    /**
     * Constructor for MarkCommand.
     *
     * @param parameterMap Map of parameters for the command.
     */
    public MarkCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        if (!super.getParameterMap().containsKey("default")) {
            throw new DukeException("Please enter a task number.");
        }

        String taskIndexString = super.getParameterMap().get("default");
        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;

            if (taskIndex < 0) {
                throw new DukeException("Task number cannot be negative.\n     "
                        + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                        taskIndex + 1,
                        tasks.size()));
            }

            Task taskMarked = tasks.markAsDone(taskIndex);
            tasks.storeTasks(storage);

            assert taskMarked != null : "Marked task should not be null";

            StringBuilder response = new StringBuilder();
            response.append("Nice! I've marked this task as done:\n");
            response.append(String.format("  %s", taskMarked.toString()));

            return response.toString();
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                    + "Please retry with a valid task number.", taskIndexString));
        }
    }
}
