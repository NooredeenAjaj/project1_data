
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class DatabaseHandler {
    private final Database db;
    private final SecurityConfigManager securityConfigManager;

    public DatabaseHandler(Database db, SecurityConfigManager securityConfigManager) {
        this.db = db;
        this.securityConfigManager = securityConfigManager;
    }

    public void logAction(String action, int recordId) {
        ActionLog actionLog = new ActionLog(securityConfigManager.getCurrentUser().getName(), recordId, action,
                LocalDateTime.now());
        db.addLog(actionLog);
    }

    public String read(int recordId) {
        Record record = db.getRecordByRecordId(recordId);
        logAction("read", recordId);
        writeToLogs();
        checkAccess(record, "read");
        return record.verboseToString();

    }

    public void write(int recordId, String comment) {
        Record record = db.getRecordByRecordId(recordId);
        logAction("write", recordId);
        checkAccess(record, "write");
        record.addComment(comment);
        saveDatabaseState();
    }

    public void create(String patientName, List<String> workerNames, String division, String description) {
        
        workerNames.add(securityConfigManager.getCurrentUser().getName());
        int recordId = db.getRecords().size() + 1;
        Record record = new Record(recordId, patientName, workerNames, division, description);
        logAction("create", recordId);
        checkAccess(record, "create");
        db.saveRecord(record);
        saveDatabaseState();
    }

    public void delete(int recordId) {
        Record record = db.getRecordByRecordId(recordId);
        logAction("delete", recordId);
        checkAccess(record, "delete");
        db.deleteRecord(record);
        saveDatabaseState();
    }

    private void checkAccess(Record record, String action) {
        if (!securityConfigManager.checkAccess(record, action)) {
            throw new SecurityException();
        }
    }

    public void saveDatabaseState() {
        writeToLogs();
        writeToRecords();
    }

    public void writeToLogs() {
        writeToFile("logs", db.getLogs());
    }

    public void writeToRecords() {
        writeToFile("records", db.getRecords());
    }

    private <E> void writeToFile(String fileName, List<E> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (E item : items) {
                if (item instanceof ActionLog) {
                    writer.write(((ActionLog) item).dbToString());
                } else {
                    writer.write(((Record) item).dbToString());

                }

                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Ett fel inträffade vid skrivning till fil: " + e.getMessage());
        }
    }

}
