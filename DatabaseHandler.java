
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

    public String read(int recordId){
        Record record = db.getRecordByRecordId(recordId);
        return record.verboseToString();
    }

    public void write(int recordId, String comment){     
        Record record = db.getRecordByRecordId(recordId);
        securityConfigManager.checkAccess(record, "write");
        record.addComment(comment);   
    }

    public void create(int recordId, int patientId, int workerId, String division, String description){

    }
    

    public void delete(int recordId){
        
    }
    public void writeRecordsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Record record : db.getAllRecords()) {
                writer.write(record.toString());
                writer.newLine(); 
            }
        } catch (IOException e) {
            System.err.println("Ett fel inträffade vid skrivning till fil: " + e.getMessage());
        }
    }
}
