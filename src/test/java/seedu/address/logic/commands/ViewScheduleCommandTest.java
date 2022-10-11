package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_22_JAN_2023;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AppointmentOnDatePredicate;
import seedu.address.model.person.Date;

public class ViewScheduleCommandTest {
    private static final LocalDate FIRST_VALID_LOCAL_DATE = DateTimeParser
            .parseLocalDateFromString(VALID_DATE_21_JAN_2023);
    private static final LocalDate SECOND_VALID_LOCAL_DATE = DateTimeParser
            .parseLocalDateFromString(VALID_DATE_22_JAN_2023);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewScheduleCommand(null));
    }

    @Test
    public void equals() {
        AppointmentOnDatePredicate firstPredicate =
                new AppointmentOnDatePredicate(new Date(FIRST_VALID_LOCAL_DATE));
        AppointmentOnDatePredicate secondPredicate =
                new AppointmentOnDatePredicate(new Date(SECOND_VALID_LOCAL_DATE));

        ViewScheduleCommand firstCommand = new ViewScheduleCommand(firstPredicate);
        ViewScheduleCommand secondCommand = new ViewScheduleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ViewScheduleCommand firstCommandCopy = new ViewScheduleCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
