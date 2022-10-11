package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.AppointmentOnDatePredicate;
import seedu.address.model.person.Person;

/**
 * Displays all the appointments on the input Date.
 */
public class ViewScheduleCommand extends Command {
    public static final String COMMAND_WORD = "viewsched";
    public static final String MESSAGE_SUCCESS = "Here are your appointments for the day";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the schedule for a specific day. "
            + "Parameters: " + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "21-Jan-2023";
    private AppointmentOnDatePredicate appointmentOnDatePredicate;

    public ViewScheduleCommand(AppointmentOnDatePredicate appointmentOnDatePredicate) {
        requireNonNull(appointmentOnDatePredicate);
        this.appointmentOnDatePredicate = appointmentOnDatePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Appointment> requiredAppointments = getMatchingAppointments(lastShownList);

        //Temporary result for ViewScheduleCommand, will add into Ui once the feature is implemented
        StringBuilder stringBuilder = new StringBuilder();
        requiredAppointments.forEach(x -> stringBuilder.append(x + " "));

        return new CommandResult(MESSAGE_SUCCESS);
    }

    private List<Appointment> getMatchingAppointments(List<Person> lastShownList) {
        List<Appointment> requiredAppointments = new ArrayList<>();
        lastShownList.stream().map(x -> x.getAppointments()).flatMap(x -> x.stream())
                .filter(appointmentOnDatePredicate).forEach(x -> requiredAppointments.add(x));
        return requiredAppointments;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewScheduleCommand// instanceof handles nulls
                && appointmentOnDatePredicate.equals(((ViewScheduleCommand) other).appointmentOnDatePredicate));
    }
}
