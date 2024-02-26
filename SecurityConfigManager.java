
public class SecurityConfigManager {
    private Person currentUser;
    private Database db;

    public SecurityConfigManager(Database db){
        this.db = db;
    }

    public boolean authenticateUser(String username, String password){
        Person person =  db.getUserByUserName(username);
         
        if(person.getPassword().equals(password)){
            setCurrentUser(person);
            return true;
        }else{
            return false;
        }
    }

    private void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
    
}
