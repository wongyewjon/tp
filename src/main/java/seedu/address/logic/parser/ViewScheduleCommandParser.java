package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.anyPrefixesPresent;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;

import seedu.address.logic.commands.ViewScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AppointmentOnDatePredicate;
import seedu.address.model.person.Date;

/**
 * Parses input arguments and creates a new ViewScheduleCommand object
 */
public class ViewScheduleCommandParser implements Parser<ViewScheduleCommand> {

    @Override
    public ViewScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_DATE);

        if (anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty() || !arePrefixesPresent(argMultimap, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewScheduleCommand.MESSAGE_USAGE));
        }

        Date requiredDate = parseDate(argMultimap.getValue(PREFIX_DATE).get()).get();
        return new ViewScheduleCommand(new AppointmentOnDatePredicate(requiredDate));
    }

    /**
     * Parses {@code String date} into a
     * {@code Date} if {@code date} is non-empty.
     */
    private Optional<Date> parseDate(String date) throws ParseException {
        assert date != null;

        if (date.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseDate(date));
    }
}
