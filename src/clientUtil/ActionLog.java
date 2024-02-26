package clientUtil;

import java.time.LocalDateTime;

public class ActionLog {
    private int userId;
    private int recordId;
    private String action;
    private LocalDateTime dateTime;

    public ActionLog(int userId, int recordId, String action, LocalDateTime dateTime){
        this.userId = userId;
        this.recordId = recordId;
        this.action = action;
        this.dateTime = dateTime;
    }

}
