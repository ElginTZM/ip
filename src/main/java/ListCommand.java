import java.util.stream.Stream;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            Ui.respond("There are no tasks to list.");
            return;
        }

        Stream<String> taskDetails = tasks.getTasks().map(task -> task.toString());
        Ui.respond(taskDetails);
    }
}
