package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code Date} matches the input {@code Date}.
 */
public class AppointmentOnDatePredicate implements Predicate<Appointment> {
    private Date date;

    public AppointmentOnDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentOnDatePredicate// instanceof handles nulls
                && date.equals(((AppointmentOnDatePredicate) other).date)); // state check
    }
}
