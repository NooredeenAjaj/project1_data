package clientUtil;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private int recordId;
    private int patientId;
    private int workerId;
    private String division;
    private String description;
    private List<String> comments;
    private final String DELIMITER = ":";

    public Record(int recordId, int patientId, int workerId, String division, String description,
            List<String> comments) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.workerId = workerId;
        this.division = division;
        this.description = description;
        this.comments = comments;
    }

    public Record(int recordId, int patientId, int workerId, String division, String description) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.workerId = workerId;
        this.division = division;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public int getRecordId() {
        return recordId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public String getDivision() {
        return division;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(recordId) + DELIMITER);
        sb.append(String.valueOf(patientId) + DELIMITER);
        sb.append(division + DELIMITER);
        sb.append(description);
        return sb.toString();
    }

    public String verboseToString(){
        String newLine = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("RecordId : " + recordId + newLine);
        sb.append("PatientId : " + patientId + newLine);
        sb.append("Division: " + division + newLine);
        sb.append("Description: " + description + newLine);
        sb.append("Comments: " + newLine);
        for(String comment : comments){
            sb.append(" - " + comment);
            sb.append(newLine);
        }

        return sb.toString();
    }
}
