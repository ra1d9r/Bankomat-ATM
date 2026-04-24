import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    String cardNumber;
    String pin;
    double balance;
    boolean isBlocked;
    int failedAttempts;
    List<Transaction> history;

    public User(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = 0.0;
        this.isBlocked = false;
        this.failedAttempts = 0;
        this.history = new ArrayList<>();
    }
}