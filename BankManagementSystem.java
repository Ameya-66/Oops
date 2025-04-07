import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();

    public void createAccount(String holder, int number) {
        accounts.put(number, new Account(holder, number));
    }

    public Account getAccount(int number) {
        return accounts.get(number);
    }

    public void showAllAccounts() {
        for (Account acc : accounts.values()) {
            System.out.println(acc);
        }
    }
}
