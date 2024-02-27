
import java.util.ArrayList;
import java.util.List;

public class Record {
    private int recordId;
    private int patientId;
    private List<Integer> workerIds;
    private String division;
    private String description;
    private List<String> comments;
    private String DELIMITER = ":";

    public Record(int recordId, int patientId, List<Integer> workerIds, String division, String description,
            List<String> comments) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.workerIds = workerIds;
        this.division = division;
        this.description = description;
        this.comments = comments;
    }

    public Record(int recordId, int patientId, List<Integer> workerIds, String division, String description) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.workerIds = workerIds;
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

    public List<Integer> getWorkerIds() {
        return workerIds;
    }

    public String getDivision() {
        return division;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(recordId) + DELIMITER);
        sb.append(String.valueOf(patientId) + DELIMITER);
        sb.append(division + DELIMITER);
        sb.append(description);
        return sb.toString();
    }

    public String verboseToString() {
        String newLine = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("RecordId : " + recordId + newLine);
        sb.append("PatientId : " + patientId + newLine);
        sb.append("Worker ids: " + newLine);
        for (Integer id : workerIds) {
            sb.append(id + newLine);
        }
        sb.append("Division: " + division + newLine);
        sb.append("Description: " + description + newLine);
        sb.append("Comments: ");
        for (String comment : comments) {
            sb.append(newLine);
            sb.append(" - " + comment);
        }

        return sb.toString();
    }

    public String dbToString() {
        DELIMITER = ";";
        StringBuilder sb = new StringBuilder();

        sb.append(recordId).append(DELIMITER)
                .append(patientId).append(DELIMITER);
        for (Integer w : workerIds) {
            sb.append(w).append("|");
        }

        sb.append(DELIMITER)
                .append(division).append(DELIMITER)
                .append(description);

        for (String comment : comments) {
            sb.append(DELIMITER).append(comment);
        }

        DELIMITER = ":";
        return sb.toString();
    }

}
