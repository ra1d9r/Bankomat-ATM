import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, User> users;
    private static User currentUser = null;

    public static void main(String[] args) {
        users = Storage.load();

        while (true) {
            try {
                if (currentUser == null) {
                    showLoginMenu();
                } else {
                    showMainMenu();
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("1 - Вход\n2 - Регистрация\n3 - Выход\nВыбор: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Номер карты: ");
                String card = scanner.nextLine();
                System.out.print("PIN: ");
                String pin = scanner.nextLine();
                try {
                    currentUser = AuthService.login(card, pin, users);
                    System.out.println("Вход выполнен.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Storage.save(users);
            } else if (choice == 2) {
                System.out.print("Номер карты: ");
                String card = scanner.nextLine();
                System.out.print("PIN: ");
                String pin = scanner.nextLine();
                try {
                    AuthService.register(card, pin, users);
                    System.out.println("Регистрация успешна.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Storage.save(users);
            } else if (choice == 3) {
                Storage.save(users);
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("Неверный ввод.");
            scanner.nextLine();
        }
    }

    private static void showMainMenu() {
        System.out.println("1 - Баланс\n2 - Пополнение\n3 - Снятие\n4 - Перевод\n5 - История\n6 - Выход\nВыбор: "