
import java.util.ArrayList;
import java.util.List;

public class Record {
    private int recordId;
    private String patientName;
    private List<String> workerNames;
    private String division;
    private String description;
    private List<String> comments;
    private String DELIMITER = ";";

    public Record(int recordId, String patientName, List<String> workerNames, String division, String description,
            List<String> comments) {
        this.recordId = recordId;
        this.patientName = patientName;
        this.workerNames = workerNames;
        this.division = division;
        this.description = description;
        this.comments = comments;
    }

    public Record(int recordId, String patientName, List<String> workerNames, String division, String description) {
        this.recordId = recordId;
        this.patientName = patientName;
        this.workerNames = workerNames;
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

    public String getPatientName() {
        return patientName;
    }

    public List<String> getWorkerNames() {
        return workerNames;
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
        sb.append(patientName + DELIMITER);
        sb.append(division + DELIMITER);
        sb.append(description);
        return sb.toString();
    }

    public String verboseToString() {
        String newLine = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("RecordId : " + recordId + newLine);
        sb.append("Patient name: " + patientName + newLine);
        sb.append("Worker names: " + newLine);
        for (String workerName : workerNames) {
            sb.append(workerName + newLine);
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
                .append(patientName).append(DELIMITER);
        for (String w : workerNames) {
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
