import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    public TransactionHistory(String type, double amount){
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getDetails(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return "|" + timestamp.format(formatter) + "| " + type + ": " + amount + "$";
    }
}
