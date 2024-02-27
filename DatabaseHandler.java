
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
        if (securityConfigManager.checkAccess(record, "read")) {
            return record.verboseToString();
        } else {
            throw new SecurityException();
        }

    }

    public void write(int recordId, String comment) {
        Record record = db.getRecordByRecordId(recordId);

        if (securityConfigManager.checkAccess(record, "write")) {
            record.addComment(comment);
        } else {
            throw new SecurityException();
        }
    }

    public void create(int recordId, int patientId, int workerId, String division, String description) {

    }

    public void delete(int recordId) {

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
