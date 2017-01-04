package eu.kaszkowiak.poc;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CyclicMeetingsCalendar implements Iterable<LocalDate> {

    private final @Getter LocalDate date;

    @Override
    public Iterator<LocalDate> iterator() {
        return new CyclicMeetingsCalendarIterator(date);
    }

    private class CyclicMeetingsCalendarIterator implements Iterator<LocalDate> {

        private LocalDate currentDate;

        public CyclicMeetingsCalendarIterator(LocalDate date) {
            currentDate = date;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public LocalDate next() {
            LocalDate nextDate = currentDate;
            do {
                nextDate = nextDate.plusDays(1);
            } while (nextDate.getDayOfWeek() != DayOfWeek.TUESDAY && nextDate.getDayOfWeek() != DayOfWeek.THURSDAY);
            currentDate = nextDate;
            return nextDate;
        }
    }
}
