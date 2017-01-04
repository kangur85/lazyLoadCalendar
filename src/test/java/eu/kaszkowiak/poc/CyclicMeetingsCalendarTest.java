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
        CyclicMeetingsCalendar calendar = new CyclicMeetingsCalendar(LocalDate.now());
        Iterator<LocalDate> calendarIterator = calendar.iterator();
        LocalDate currentDate = calendar.getStartDate();
        assertNotNull(currentDate);
        LocalDate nextDate = calendarIterator.next();
        assertTrue(currentDate.isBefore(nextDate));
        assertTrue(calendarIterator.hasNext());
    }

    @Test
    void shouldHaveDifferentIterators() {
        CyclicMeetingsCalendar calendar = new CyclicMeetingsCalendar(LocalDate.of(2017,1,1));
        Iterator<LocalDate> iterator1 = calendar.iterator();
        Iterator<LocalDate> iterator2 = calendar.iterator();
        iterator1.next();
        iterator1.next();
        LocalDate date2 = iterator2.next();
        LocalDate date1 = iterator1.next();
        assertEquals(LocalDate.of(2017,1,10), date1);
        assertEquals(LocalDate.of(2017,1,3), date2);
    }

}