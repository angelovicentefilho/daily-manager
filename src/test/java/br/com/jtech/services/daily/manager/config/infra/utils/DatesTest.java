package br.com.jtech.services.daily.manager.config.infra.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatesTest {

    @Test
    void testFromWithValidDateTimeString() {
        String dateTimeString = "2022-01-01 12:00:00";
        LocalDateTime expectedDateTime = LocalDateTime.of(2022, 1, 1, 12, 0, 0);
        LocalDateTime actualDateTime = Dates.from(dateTimeString);
        assertEquals(expectedDateTime, actualDateTime);
    }

    @Test
    void testFromWithInvalidDateTimeString() {
        String dateTimeString = "2022-01-01T12:00:00"; // Invalid format
        assertThrows(DateTimeParseException.class, () -> Dates.from(dateTimeString));
    }
}