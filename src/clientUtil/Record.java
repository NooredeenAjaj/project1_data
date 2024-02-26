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

    public Record(int recordId, int patientId, int workerId, String division, String description, List<String> comments) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.workerId = workerId;
        this.division = division;
        this.description = description;
        this.comments = comments;
    }

    public void addComment(String comment){
        comments.add(comment);
    }

    public int getRecordId(){
        return recordId;
    }

    public int getPatientId(){
        return patientId;
    }

    public int getWorkerId(){
        return workerId;
    }

    public String getDivision(){
        return division;
    }

    public String getDescription(){
        return description;
    }

}
