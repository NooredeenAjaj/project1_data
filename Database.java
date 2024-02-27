
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Database {
    private final String DELIMITER = ";";
    private File usersFile = new File("personInformation");
    private File recordsFile = new File("records");
    private File logFile = new File("logs");
    private List<Person> users = new ArrayList<>();
    private List<Record> records = new ArrayList<>();
    private List<ActionLog> logs = new ArrayList<>();

    public void readDatabase() {
        loadUsers();
        loadRecords();
        loadLogs();
    }

    private void loadUsers() {
        try {
            Scanner scanner = new Scanner(usersFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
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

    private void loadRecords() {
        try {
            Scanner scanner = new Scanner(recordsFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    String[] attributes = line.split(DELIMITER);
                    int recordId = Integer.parseInt(attributes[0]);
                    int patientId = Integer.parseInt(attributes[1]);
                    List<Integer> workerId = Arrays.stream(attributes[2].split("\\|")).map(Integer::parseInt)
                            .collect(Collectors.toList());
                    String division = attributes[3];
                    String description = attributes[4];
                    List<String> comments = new ArrayList<>();

                    for (int i = 5; i < attributes.length; i++) {
                        if (attributes[i] != null) {
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

    private void loadLogs() {
        try {
            Scanner scanner = new Scanner(logFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    String[] attributes = line.split(DELIMITER);
                    int workerId = Integer.parseInt(attributes[0]);
                    int patientId = Integer.parseInt(attributes[1]);
                    String action = attributes[2];
                    LocalDateTime time = LocalDateTime.parse((CharSequence) attributes[3]);

                    logs.add(new ActionLog(workerId, patientId, action, time));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Person getUserByUserName(String username) {
        Optional<Person> user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny();
        return user.orElse(null);
    }

    public List<Record> getRecordsByWorkerId(int workerId) {
        List<Record> foundRecords = records.stream()
                .filter(r -> r.getWorkerIds().contains(workerId))
                .toList();

        return foundRecords;
    }

    public List<Record> getRecordsByPatientId(int patientId) {
        List<Record> foundRecords = records.stream()
                .filter(r -> r.getPatientId() == patientId)
                .toList();

        return foundRecords;
    }

    public Record getRecordByRecordId(int recordId) {
        Optional<Record> foundRecord = records.stream()
                .filter(r -> r.getRecordId() == recordId)
                .findAny();

        return foundRecord.orElse(null);
    }

    public void saveRecord(Record record) {
        records.add(record);
    }

    public void deleteRecord(Record record) {
        records.remove(record);
    }

    public List<ActionLog> getLogs() {
        return logs;
    }

    public List<Record> getRecords() {
        return records;
    }

}
