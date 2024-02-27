import java.time.LocalDateTime;

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

        StringBuilder sb = new StringBuilder();

        sb.append(userId).append(";")
                .append(recordId).append(";")
                .append(action).append(";")
                .append(dateTime).append(";");

        return sb.toString();
    }

}
