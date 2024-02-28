import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ClientInfoStatus;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientInput {
    private Database db;
    private String patientName;
    private DatabaseHandler dbHandler;
    private SecurityConfigManager securityManager;
    private PrintWriter out;
    private BufferedReader in;

    private final String readCommand = "read [recordId]";
    private final String writeCommand = "write [recordId] [comment]";
    private final String listCommand = "list [patientId]";
    private final String createCommand = "create [patientId] [workerId|workerId2|*] [division] [description]";
    private final String deleteCommand = "delete [recordId]";
    private String[] menu = {
            "Please choose an option: ",
            listCommand,
            readCommand,
            writeCommand,
            createCommand,
            deleteCommand
    };

    public ClientInput(Database db, BufferedReader in, PrintWriter out) {
        this.db = db;
        this.securityManager = new SecurityConfigManager(db);
        this.dbHandler = new DatabaseHandler(db, securityManager);
        this.in = in;
        this.out = out;
    }


    public void Menu() {

        for (int i = 0; i < menu.length; i++) {
            out.println(menu[i]);
        }
        out.println("\n");

    }

    public void getUserInput() {
        String[] inputArgs;
        Menu();
        String clientMsg;
        try {
            while ((clientMsg = in.readLine()) != null) {
                inputArgs = clientMsg.split(" ");
                String action = inputArgs[0];
                handleAction(inputArgs, action);
            }
            in.close();
            out.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void handleAction(String[] inputArgs, String action) {
        try {
            switch (action) {
                case "read":
                    handleRead(inputArgs);
                    break;
                case "write":
                    handleWrite(inputArgs);
                    break;
                case "delete":
                    handleDelete(inputArgs);
                    break;
                case "create":
                    handleCreate(inputArgs);
                    break;
                case "list":
                    handleList(inputArgs);
                    break;
                default:
                    break;
            }
        } catch (SecurityException securityException) {
            printOut("You don't have the permission to perform this action");
        } catch (Exception unexpected) {
            out.println("Invalid arguments for create");
            out.println("Excpected format: " + createCommand);
            out.println("\n");
        }
    }

    private void printOut(String output) {
        out.println(output);
        out.println("\n");
    }

    private void handleList(String[] inputArgs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleList'");
    }

    private void handleCreate(String[] inputArgs) {
        int patientId = Integer.parseInt(inputArgs[1]);
        List<String> workerNames = Arrays.stream(inputArgs[2].split("\\|"))
                .collect(Collectors.toList());

        String divisionAndDescription = String.join(" ", Arrays.copyOfRange(inputArgs, 3, inputArgs.length));
        String[] separated = divisionAndDescription.split("\" \"");
        String division = separated[0].trim().replaceAll("\"", "");
        String description = separated[1].trim().replaceAll("\"", "");
        dbHandler.create(patientName, workerNames, division, description);
        printOut("Successfully created new record");
    }

    private void handleDelete(String[] inputArgs) {
        int recordId = Integer.parseInt(inputArgs[1]);
        dbHandler.delete(recordId);
        printOut("Successfully deleted record");
    }

    private void handleRead(String[] args) {
        String recordInfo = dbHandler.read(Integer.parseInt(args[1]));
        printOut(recordInfo);
    }

    private void handleWrite(String[] args) {
        int recordId = Integer.parseInt(args[1]);
        String comment = String.join(" ", Arrays.asList(args).subList(2, args.length)).replaceAll("\"", "");
        dbHandler.write(recordId, comment);
        printOut("Successfully wrote to record: " + recordId);
    }

}