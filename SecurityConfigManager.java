
public class SecurityConfigManager {
    private Person currentUser;
    private Database db;

    public SecurityConfigManager(Database db){
        this.db = db;
    }

    public boolean authenticateUser(String username, String password){
            Person person =  db.getUserByUserName(username);
         
            if(person != null && person.getPassword().equals(password)){
                setCurrentUser(person);
                return true;
            }

        return false;

    }

    public boolean checkAccess(Record record, String action){
        // "Universal" checks
        if(
            (currentUser instanceof Doctor || currentUser instanceof Nurse) && (
            !currentUser.getDivision().equals(record.getDivision()) ||
            !record.getWorkerIds().contains(currentUser.getID()))
        )
        {
            return false;
        }

        switch (action) {
            case "read":
                if(currentUser instanceof Nurse || currentUser instanceof Doctor || currentUser instanceof GovernmentAgency){
                    return true;
                }

                if(currentUser instanceof Patient){
                    return currentUser.getID() == record.getPatientId();
                }
                break;

            case "write":
                if(currentUser instanceof Nurse || currentUser instanceof Doctor){
                    return true;
                }
                break;

            case "create":
                if(currentUser instanceof Doctor){
                    return true;
                }
                break;

            case "delete":
                if(currentUser instanceof GovernmentAgency){
                    return true;
                }
                break;

            default:
                break;
        }

        return false;
    }

    private void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
    
}
