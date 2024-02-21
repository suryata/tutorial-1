package id.ac.ui.cs.advprog.eshop.repository;
import java.util.UUID;

public class UniqueIdGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}

