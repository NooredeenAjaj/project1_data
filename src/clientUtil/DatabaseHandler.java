package clientUtil;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private final Database db;

    public DatabaseHandler(Database db) {
        this.db = db;
    }

    public String read(int recordId){
        Record record = db.getRecordByRecordId(recordId);
        return record.verboseToString();
    }

    public void write(int recordId, String comment){     
        db.getRecordByRecordId(recordId).addComment(comment);   
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
