import java.sql.ClientInfoStatus;
import java.util.Scanner;

public class ClientInput {
    private Database db;
    private int patientId;
    private DatabaseHandler dbHandler;
    private SecurityConfigManager securityManager;
    private final String readCommand = "read [recordId]";
    private final String writeCommand = "write [recordId]";
    private final String listCommand = "list [patientId]";
    private final String createCommand = "create [patientId] [workerId] [division] [description]";
    private final String deleteCommand = "delete [recordId]";
    private String[] menu = {
            "Please choose an option: ",
            listCommand,
            readCommand,
            writeCommand,
            createCommand,
            deleteCommand
    };

public ClientInput(Database db ){
    this.db = db;
    db.readDatabase();
    this.dbHandler = new DatabaseHandler(db);
    this.securityManager = new SecurityConfigManager(db);
}

    public boolean login(String username, String password) {
        return securityManager.authenticateUser(username, password);
    }

    public void Menu() {

            for(int i = 0; i < menu.length; i++ ) 
            {
                    System.out.println(menu[i]);
            }

    }

    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String[] inputArgs;
        

        while (true) {
            Menu();

            if (scanner.hasNextLine()) {

                inputArgs = scanner.nextLine().split(" ");
                
                String action = inputArgs[0];
                switch (action) {
                    case "read":
                        handleRead(inputArgs);  
                            
                        
                        break;
                    case "write": 
                        handleWrite(inputArgs);

                    case "create":
                        handleCreate(inputArgs);



                        break;

                    case "delete":
                        handleDelete(inputArgs);

                    case "list":
                        handleList(inputArgs);
                    default:
                        break;
                }

   
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
        }
   
        }
    }
    
    private void handleList(String[] inputArgs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleList'");
    }

    private void handleCreate(String[] inputArgs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCreate'");
    }

    private void handleDelete(String[] inputArgs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleDelete'");
    }

    private void handleRead(String[] args){
        try{
            String recordInfo = dbHandler.read(Integer.parseInt(args[1]));
            System.out.println(recordInfo);
        }catch(Exception unexpected){
            System.out.println("Invalid arguments for read");
            System.out.println("Excpected format: " + readCommand);
        }
    }

    private void handleWrite(String[] args){
        System.out.println("handle write");
    }


}