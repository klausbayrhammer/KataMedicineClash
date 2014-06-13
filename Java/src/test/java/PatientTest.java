import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatientTest {

    @Test
    public void noClashWithNoMedicine() throws Exception {
        Patient patient = new Patient();
        Collection<LocalDate> clashes = patient.clash(Collections.emptyList());
        assertTrue(clashes.isEmpty());
    }

    @Test
    public void singleClashWithTwoMedicines() throws Exception {
        Medicine m1 = new Medicine("M1");
        LocalDate dispenseDate = LocalDate.now();
        m1.addPrescription(new Prescription(dispenseDate,0));
        Medicine m2 = new Medicine("M2");
        m2.addPrescription(new Prescription(dispenseDate,0));

        Patient patient = new Patient();
        patient.addMedicine(m1);
        patient.addMedicine(m2);

        Collection<LocalDate> clashes = patient.clash(Arrays.asList("M1", "M2"));

        assertEquals(1, clashes.size());
        assertEquals(dispenseDate, clashes.iterator().next());
    }

    @Test
    public void noClashWithTwoMedicines() throws Exception {
        Medicine m1 = new Medicine("M1");
        LocalDate dispenseDate = LocalDate.now();
        m1.addPrescription(new Prescription(dispenseDate,1));
        Medicine m2 = new Medicine("M2");
        m2.addPrescription(new Prescription(dispenseDate.minusDays(100),1));

        Patient patient = new Patient();
        patient.addMedicine(m1);
        patient.addMedicine(m2);

        Collection<LocalDate> clashes = patient.clash(Arrays.asList("M1", "M2"));

        assertEquals(0, clashes.size());
    }

    @Test
    public void twoClashesWithTwoMedicines() throws Exception {
        Medicine m1 = new Medicine("M1");
        LocalDate dispenseDate = LocalDate.now();
        m1.addPrescription(new Prescription(dispenseDate.minusDays(10),10));
        Medicine m2 = new Medicine("M2");
        m2.addPrescription(new Prescription(dispenseDate.minusDays(10),10));

        Patient patient = new Patient();
        patient.addMedicine(m1);
        patient.addMedicine(m2);

        Collection<LocalDate> clashes = patient.clash(Arrays.asList("M1", "M2"));

        assertEquals(11, clashes.size());
    }

}
