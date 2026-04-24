import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static final String FILE_NAME = "atm_data.dat";

    @SuppressWarnings("unchecked")
    public static Map<String, User> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (Exception e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void save(Map<String, User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}