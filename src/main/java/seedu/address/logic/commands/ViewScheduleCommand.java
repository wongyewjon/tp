package seedu.address.logic.commands;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

class ViewScheduleCommand extends Command {
    public static final String COMMAND_WORD = "viewsched";

    public static final String MESSAGE_SUCCESS = "Here are your appointments for the day";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}