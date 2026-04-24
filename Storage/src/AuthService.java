import java.util.Map;

public class AuthService {
    public static User login(String cardNumber, String pin, Map<String, User> users) {
        User user = users.get(cardNumber);
        if (user == null) {
            throw new IllegalArgumentException("Карта не найдена.");
        }
        if (user.isBlocked) {
            throw new SecurityException("Карта заблокирована.");
        }
        if (!user.pin.equals(pin)) {
            user.failedAttempts++;
            if (user.failedAttempts >= 3) {
                user.isBlocked = true;
                throw new SecurityException("Карта заблокирована из-за 3 неверных попыток.");
            }
            throw new IllegalArgumentException("Неверный PIN. Попыток осталось: " + (3 - user.failedAttempts));
        }
        user.failedAttempts = 0;
        return user;
    }

    public static void register(String cardNumber, String pin, Map<String, User> users) {
        if (users.containsKey(cardNumber)) {
            throw new IllegalArgumentException("Карта уже существует.");
        }
        users.put(cardNumber, new User(cardNumber, pin));
    }
}