import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Prescription {
    
    private LocalDate dispenseDate;
    private int daysSupply = 30;
    
    public Prescription(LocalDate dispenseDate, int daysSupply) {
        this.dispenseDate = dispenseDate;
        this.daysSupply = daysSupply;
    }

    public Collection<LocalDate> getDispenseDates() {
        ArrayList<LocalDate> dispenseDates = new ArrayList<>();
        for (int i = 0; i <= daysSupply; i++) {
            dispenseDates.add(dispenseDate.plusDays(i));
        }
        return dispenseDates;
    }
}
