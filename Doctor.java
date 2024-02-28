

public class Doctor extends Person {

    String treatingPatient;

    public Doctor(String name, String division, String treatingPatient) {
        super(name, division);
        this.treatingPatient = treatingPatient;
    }

    public String getTreatingPatientName() {
      return treatingPatient;
    }

}
