
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
        switch (action) {
            case "read":
                

                if(currentUser instanceof Nurse || currentUser instanceof Doctor){
                    return currentUser.getID() == record.getWorkerId();
                }

                
                
                break;
        
            default:
                break;
        }
    }

    private void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
    
}
