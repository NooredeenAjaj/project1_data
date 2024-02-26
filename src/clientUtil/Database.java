package clientUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private final String DELIMITER = ";";
    private File usersFile = new File("../Database/personInformation");
    private File recordsFile = new File("../Database/journals");
    private File logFile = new File("../Database/logs");
    private List<Person> users = new ArrayList<>();
    private List<Record> records = new ArrayList<>();
    private List<ActionLog> logs = new ArrayList<>();

    public void readDatabase(){
        loadUsers();
        loadRecords();
        loadLogs();
    }

    private void loadUsers(){
        try {
            Scanner scanner = new Scanner(usersFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() > 0){
                    String[] attributes = line.split(DELIMITER);
                    String id = attributes[0];
                    String username = attributes[1];
                    String role = attributes[2];
                    String division = attributes[3];
                    String password = attributes[4];
                    
                    switch (role) {
                        case "Nurse":
                            users.add(new Nurse(username, password, division, Integer.parseInt(id)));
                            break;
                        case "Doctor":
                            users.add(new Doctor(username, password, division, Integer.parseInt(id)));
                            break;
                        case "Patient":
                            users.add(new Patient(username, password, division, Integer.parseInt(id)));
                            break;
                        case "GovernmentAgency":
                            users.add(new GovernmentAgency(username, password, Integer.valueOf(id)));
                            break;
        
                    }
                }
                

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private void loadRecords(){
        try {
            Scanner scanner = new Scanner(recordsFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() > 0){
                    String[] attributes = line.split(DELIMITER);
                    int recordId = Integer.parseInt(attributes[0]);
                    int patientId = Integer.parseInt(attributes[1]);
                    int workerId = Integer.parseInt(attributes[2]);
                    String division = attributes[3];
                    String description = attributes[4];
                    List<String> comments = new ArrayList<>();

                    for(int i = 5; i < attributes.length; i++){
                        if(attributes[i] != null){
                            comments.add(attributes[i]);
                        }
                    }
                    
                    records.add(new Record(recordId, patientId, workerId, division, description, comments));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private void loadLogs(){
        try {
            Scanner scanner = new Scanner(logFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() > 0){
                    String[] attributes = line.split(DELIMITER);
                    int workerId = Integer.parseInt(attributes[0]);
                    int patientId = Integer.parseInt(attributes[1]);
                    String action = attributes[2];
                    LocalDateTime time = LocalDateTime.parse(attributes[3]);
                    
                    logs.add(new ActionLog(workerId, patientId, action, time));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void getUsers(){

    }

    public List<Record> getRecordsByWorkerId(int workerId){
        List<Record> foundRecords = records.stream()
            .filter(r -> r.getWorkerId() == workerId)
            .toList();

        return foundRecords;
    }

    public List<Record> getRecordsByPatientId(int patientId){
        List<Record> foundRecords = records.stream()
            .filter(r -> r.getPatientId() == patientId)
            .toList();

        return foundRecords;
    }

    public void getLogs(){

    }
}
