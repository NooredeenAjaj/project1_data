import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActionLog {
    private int userId;
    private int recordId;
    private String action;
    private LocalDateTime dateTime;
    private final String DELIMITER = ":";

    public ActionLog(int userId, int recordId, String action, LocalDateTime dateTime) {
        this.userId = userId;
        this.recordId = recordId;
        this.action = action;
        this.dateTime = dateTime;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(userId + DELIMITER);
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

        sb.append(userId).append(";")
                .append(recordId).append(";")
                .append(action).append(";")
                .append(formattedDateTime).append(";");

        return sb.toString();
    }

}
