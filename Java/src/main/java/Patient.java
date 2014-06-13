

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Patient {

    private Collection<Medicine> medicines = new ArrayList<>();

    public void addMedicine(Medicine medicine) {
        this.medicines.add(medicine);
    }

    public Collection<LocalDate> clash(Collection<String> medicineNames) {
        return clash(medicineNames, 90);
    }

    public Collection<LocalDate> clash(Collection<String> medicineNames, int daysBack) {
        if (medicineNames.isEmpty()) {
            return Collections.emptyList();
        }
        Stream<LocalDate> prescriptionDates = getAllPrescriptionDates();
        return findDateClashes(prescriptionDates);
    }

    private Collection<LocalDate> findDateClashes(Stream<LocalDate> prescriptionDates) {
        Map<LocalDate, List<LocalDate>> groupedbyDate = prescriptionDates.collect(Collectors.groupingBy(d -> d));

        Stream<LocalDate> map = groupedbyDate.entrySet().stream().filter(x -> x.getValue().size() > 1).map(Map.Entry::getKey);
        return map.collect(Collectors.toList());
    }

    private Stream<LocalDate> getAllPrescriptionDates() {
        Stream<Prescription> prescriptionStream = medicines.stream().flatMap(o -> o.getPrescriptions().stream());
        return prescriptionStream.flatMap(o -> o.getDispenseDates().stream());
    }


}
