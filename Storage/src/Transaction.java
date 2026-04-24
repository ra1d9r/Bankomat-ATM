import java.io.Serializable;

public record Transaction(String type, double amount, String date) implements Serializable {}