import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class PrescriptionTest {

    @Test
    public void getDispenseDays() {
        Prescription prescription = new Prescription(LocalDate.now().minusDays(1), 1);

        Collection<LocalDate> dispenseDates = prescription.getDispenseDates();

        assertEquals(dispenseDates.size(), 2);
    }
    @Test
    public void getDispenseDaysLongerPeriod() {
        Prescription prescription = new Prescription(LocalDate.now().minusDays(5), 5);

        Collection<LocalDate> dispenseDates = prescription.getDispenseDates();

        assertEquals(dispenseDates.size(), 6);
    }
}