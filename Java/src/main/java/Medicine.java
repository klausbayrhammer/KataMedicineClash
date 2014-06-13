

import java.util.ArrayList;
import java.util.Collection;

public class Medicine {
    
    private Collection<Prescription> prescriptions = new ArrayList<>();
    
    private final String name;

    public Medicine(String name) {
        this.name = name;
    }

    public Collection<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }
}
