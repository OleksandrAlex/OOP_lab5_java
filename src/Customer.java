import java.io.*;
import java.util.List;

enum Bank {
    MONOBANK, PRYVATBANK, SENSEBANK
}

class Customer implements Serializable {
    private String fullName;
    private Bank bank;
    private String creditCardNumber;
    private int limit;
    private int accountBalance;

    public Customer(String fullName, Bank bank, String creditCardNumber, int limit, int accountBalance) {
        this.fullName = fullName;
        this.bank = bank;
        this.creditCardNumber = creditCardNumber;
        this.limit = limit;
        this.accountBalance = accountBalance;
    }

    public String getFullName() {
        return fullName;
    }

    public Bank getBank() {
        return bank;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getLimit() {
        return limit;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", bank=" + bank +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", limit=" + limit +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
