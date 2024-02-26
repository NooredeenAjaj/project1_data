import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ClientInfoStatus;
import java.util.Scanner;

public class ClientInput {
    private Database db;
    private int patientId;
    private DatabaseHandler dbHandler;
    private SecurityConfigManager securityManager;
    private PrintWriter out;
    private BufferedReader in;

    private final String readCommand = "read [recordId]";
    private final String writeCommand = "write [recordId] [comment]";
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

public ClientInput(Database db, BufferedReader in, PrintWriter out){
    this.db = db;
    db.readDatabase();
    this.securityManager = new SecurityConfigManager(db);
    this.dbHandler = new DatabaseHandler(db, securityManager);
    this.in = in;
    this.out = out;
}

    public boolean login(String username, String password) {
        return securityManager.authenticateUser(username, password);
    }

    public void Menu() {

            for(int i = 0; i < menu.length; i++ ) 
            {
                    out.println(menu[i]);
            }
            out.println("\n");

    }

    public void getUserInput() {
        String[] inputArgs;
        Menu();
        String clientMsg;
        try{
            while ((clientMsg = in.readLine()) != null) {
                inputArgs = clientMsg.split(" ");
                String action = inputArgs[0];
                System.out.println(action);
                switch (action) {
                    case "read":
                        handleRead(inputArgs);  
                        break;
                    case "write": 
                        handleWrite(inputArgs);
                        break;
                    case "create":
                        handleCreate(inputArgs);
                        break;
                    case "delete":
                        handleDelete(inputArgs);
                        break;
                    case "list":
                        handleList(inputArgs);
                        break;
                    default:
                        break;
                }
            }
            in.close();
            out.close();
    
        }catch(IOException e){
            System.out.println(e);
        }

    }
    
    private void printOut(String output){
        out.println(output);
        out.println("\n");
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
            printOut(recordInfo);
        }catch(SecurityException securityException){
            printOut("You don't have the permission to read this record");
        }catch(Exception unexpected){
            printOut("Invalid arguments for read");
            printOut("Excpected format: " + readCommand);
        }
    }

    private void handleWrite(String[] args){
        try{
            int recordId = Integer.parseInt(args[1]);
            String comment = args[2];
            dbHandler.write(recordId, comment);
            printOut("Successfully wrote to record: " + recordId);
        }catch(Exception unexpected){
            printOut("Invalid arguments for read");
            printOut("Excpected format: " + readCommand);
        }
    }


}