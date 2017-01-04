package eu.kaszkowiak.poc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CyclicMeetingsCalendarTest {

    @Test
    void shouldGetDate() {
        Iterator<LocalDate> calendar = new CyclicMeetingsCalendar(LocalDate.of(2016, 9, 16)).iterator();
        assertNotNull(calendar);
    }

    @Test
    void shouldGetTuesday() {
        Iterator<LocalDate> calendar = new CyclicMeetingsCalendar(LocalDate.of(2016, 9, 19)).iterator();
        LocalDate tuesday = LocalDate.of(2016, 9, 20);
        assertEquals(tuesday, calendar.next());
    }

    @Test
    void shouldGetThursday() {
        Iterator<LocalDate> calendar = new CyclicMeetingsCalendar(LocalDate.of(2016, 9, 19)).iterator();
        LocalDate thursday = LocalDate.of(2016, 9, 22);
        calendar.next();
        assertEquals(thursday, calendar.next());
    }

    @Test
    void shouldGetNextForAnyDate() {
        Iterator<LocalDate> calendar = new CyclicMeetingsCalendar(LocalDate.now()).iterator();
        LocalDate nextDate = calendar.next();
        assertNotNull(nextDate);
        assertTrue(nextDate.isBefore(calendar.next()));
    }

}