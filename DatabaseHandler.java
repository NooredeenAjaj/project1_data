
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private final Database db;
    private final SecurityConfigManager securityConfigManager;

    public DatabaseHandler(Database db, SecurityConfigManager securityConfigManager) {
        this.db = db;
        this.securityConfigManager = securityConfigManager;
    }

    public String read(int recordId) {
        Record record = db.getRecordByRecordId(recordId);
        checkAccess(record, "read");
        return record.verboseToString();

    }

    public void write(int recordId, String comment) {
        Record record = db.getRecordByRecordId(recordId);
        checkAccess(record, "write");
        record.addComment(comment);
    }

    public void create(int recordId, int patientId, List<Integer> workerIds, String division, String description) {
        Record record = new Record(recordId, patientId, workerIds, division, description);
        checkAccess(record, "create");
        db.saveRecord(record);
    }

    public void delete(int recordId) {
        Record record = db.getRecordByRecordId(recordId);
        checkAccess(record, "delete");
        db.deleteRecord(record);
    }

    private void checkAccess(Record record, String action) {
        if (!securityConfigManager.checkAccess(record, action)) {
            throw new SecurityException();
        }
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
