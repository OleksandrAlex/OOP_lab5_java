import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Alice Brown", Bank.MONOBANK, "1234567890123456", 5000, 6000));
        customers.add(new Customer("John Smith", Bank.PRYVATBANK, "2345678901234567", 3000, 2500));
        customers.add(new Customer("Jane Doe", Bank.SENSEBANK, "3456789012345678", 7000, 8000));
        customers.add(new Customer("Bob White", Bank.MONOBANK, "4567890123456789", 10000, 12000));

        // Виклик наступних завдань
        showCustomers(customers);
        System.out.println("\n\n");
        List<Customer> custmerswithbalance = customersWithBalanceExceedingLimit(customers, Bank.MONOBANK);
        showCustomers(custmerswithbalance);
        System.out.println("\n\n");

        // Сортування за ім'ям клієнта
        customers.sort((a, b) -> a.getFullName().compareTo(b.getFullName()));

// Виведення клієнтів із фільтрацією
        customers.stream()
                .filter(c -> c.getBank() == Bank.MONOBANK)
                .forEach(System.out::println);
        System.out.println("\n\n");

// Накопичення балансу всіх клієнтів
        int totalBalance = customers.stream()
                .mapToInt(Customer::getAccountBalance)
                .sum();

        System.out.println("Total balance: " + totalBalance);


    }

    private static void showCustomers(List<Customer> customers) {
        customers.forEach(System.out::println);
    }

    public static void serializeCustomers(List<Customer> customers, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(customers);
            System.out.println("Customers serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> deserializeCustomers(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // а) Список клієнтів одного банку, у яких на рахунку більше, ніж ліміт
    public static List<Customer> customersWithBalanceExceedingLimit(List<Customer> customers, Bank bank) {
        return customers.stream()
                .filter(c -> c.getBank() == bank && c.getAccountBalance() > c.getLimit())
                .collect(Collectors.toList()); // Замість .toList()
    }

    // б) Список клієнтів, чий номер картки в заданому інтервалі
    public static List<Customer> customersWithCardInRange(List<Customer> customers, String start, String end) {
        return customers.stream()
                .filter(c -> c.getCreditCardNumber().compareTo(start) >= 0 && c.getCreditCardNumber().compareTo(end) <= 0)
                .collect(Collectors.toList()); // Замість .toList()
    }

    // в) Список клієнтів, у яких баланс наближається до ліміту
    public static List<Customer> customersNearLimit(List<Customer> customers, int proximity) {
        return customers.stream()
                .filter(c -> Math.abs(c.getAccountBalance() - c.getLimit()) <= proximity)
                .collect(Collectors.toList()); // Замість .toList()
    }



}
