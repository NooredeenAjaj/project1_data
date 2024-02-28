
public class SecurityConfigManager {
    private Person currentUser;
    private Database db;

    public SecurityConfigManager(Database db) {
        this.db = db;
    }

    public boolean checkAccess(Record record, String action) {
        // Doctor and nurse have to be in the same division and be associated with the
        // record

        switch (action) {
            case "read":

                if (currentUser instanceof GovernmentAgency) {
                    return true;
                }

                if ((currentUser instanceof Nurse || currentUser instanceof Doctor) &&
                        (currentUser.getDivision().equals(record.getDivision()) ||
                        record.getWorkerNames().contains(currentUser.getName()))) {
                    return true;
                }

                if (currentUser instanceof Patient) {
                    return currentUser.getName() == record.getPatientName();
                }
                break;

            case "write":
                if ((currentUser instanceof Doctor || currentUser instanceof Nurse) &&
                        currentUser.getDivision().equals(record.getDivision())) {
                    return true;
                }
                break;

            case "create":
                if (currentUser instanceof Doctor 
                    && record.getWorkerNames().contains(currentUser.getName())) {
                    return true;
                }
                break;

            case "delete":
                if (currentUser instanceof GovernmentAgency) {
                    return true;
                }
                break;

            default:
                break;
        }

        return false;
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

}
