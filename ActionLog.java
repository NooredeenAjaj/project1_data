import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActionLog {
    private String workerName;
    private int recordId;
    private String action;
    private LocalDateTime dateTime;
    private final String DELIMITER = ":";

    public ActionLog(String workerName, int recordId, String action, LocalDateTime dateTime) {
        this.workerName = workerName;
        this.recordId = recordId;
        this.action = action;
        this.dateTime = dateTime;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(workerName + DELIMITER);
        sb.append(recordId + DELIMITER);
        sb.append(action + DELIMITER);
        sb.append(dateTime.toString());
        return sb.toString();
    }

    public String dbToString() {
                
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        
        // Format the LocalDateTime object
        String formattedDateTime = dateTime.format(formatter);

        StringBuilder sb = new StringBuilder();

        sb.append(workerName).append(";")
                .append(recordId).append(";")
                .append(action).append(";")
                .append(formattedDateTime).append(";");

        return sb.toString();
    }

}
