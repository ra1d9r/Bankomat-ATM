import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static void deposit(User user, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше нуля.");
        }
        user.balance += amount;
        addTransaction(user, "Пополнение", amount);
    }

    public static void withdraw(User user, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше нуля.");
        }
        if (amount > user.balance) {
            throw new IllegalArgumentException("Недостаточно средств.");
        }
        user.balance -= amount;
        addTransaction(user, "Снятие", amount);
    }

    public static void transfer(User sender, User receiver, double amount) {
        if (receiver == null) {
            throw new IllegalArgumentException("Получатель не найден.");
        }
        if (sender.cardNumber.equals(receiver.cardNumber)) {
            throw new IllegalArgumentException("Нельзя перевести самому себе.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше нуля.");
        }
        if (amount > sender.balance) {
            throw new IllegalArgumentException("Недостаточно средств.");
        }

        sender.balance -= amount;
        receiver.balance += amount;

        addTransaction(sender, "Перевод на " + receiver.cardNumber, amount);
        addTransaction(receiver, "Перевод от " + sender.cardNumber, amount);
    }

    private static void addTransaction(User user, String type, double amount) {
        String date = LocalDateTime.now().format(formatter);
        user.history.add(new Transaction(type, amount, date));
        if (user.history.size() > 10) {
            user.history.remove(0);
        }
    }
}
