package eu.kaszkowiak.poc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CyclicMeetingsCalendarTest {

    @Test
    public void shouldGetDate() {
        Iterator<LocalDate> calendar = new CyclicMeetingsCalendar(LocalDate.of(2016, 9, 16)).iterator();
        assertThat(calendar).isNotNull();
    }

    @Test
    public void shouldGetTuesday() {
        Iterator<LocalDate> calendar = new CyclicMeetingsCalendar(LocalDate.of(2016, 9, 19)).iterator();
        LocalDate tuesday = LocalDate.of(2016, 9, 20);
        assertThat(calendar.next()).isEqualTo(tuesday);
    }

    @Test
    public void shouldGetThursday() {
        Iterator<LocalDate> calendar = new CyclicMeetingsCalendar(LocalDate.of(2016, 9, 19)).iterator();
        LocalDate thursday = LocalDate.of(2016, 9, 22);
        calendar.next();
        assertThat(calendar.next()).isEqualTo(thursday);
    }

    @Test
    public void shouldGetNextForAnyDate() {
        CyclicMeetingsCalendar calendar = new CyclicMeetingsCalendar(LocalDate.now());
        Iterator<LocalDate> calendarIterator = calendar.iterator();
        LocalDate currentDate = calendar.getStartDate();
        assertThat(currentDate).isNotNull();

        LocalDate nextDate = calendarIterator.next();
        assertThat(currentDate).isBefore(nextDate);
        assertThat(calendarIterator.hasNext()).isTrue();
    }

    @Test
    public void shouldHaveDifferentIterators() {
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