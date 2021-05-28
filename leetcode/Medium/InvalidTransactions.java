import java.util.*;

// 1169 https://leetcode.com/problems/invalid-transactions/
public class InvalidTransactions {


    public static void main(String[] args) {
        String[] txn = new String[]{"bob,689,1910,barcelona", "alex,696,122,bangkok", "bob,832,1726,barcelona", "bob,820,596,bangkok", "chalicefy,217,669,barcelona", "bob,175,221,amsterdam"};
        List<String> strings = new InvalidTransactions().invalidTransactions(txn);
        System.out.println(strings);
    }

    public List<String> invalidTransactions(String[] txns) {
        Map<String, List<Transaction>> nameTransactionMap = new HashMap<>();
        List<String> invalid = new ArrayList<>();
        for (String transaction : txns) {
            String[] split = transaction.split(",");
            //{name},{time},{amount},{city}
            int amount = Integer.parseInt(split[2]);
            nameTransactionMap.putIfAbsent(split[0], new ArrayList<>());
            Transaction txn = new Transaction(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[3]);
            txn.invalid = amount > 1000;
            nameTransactionMap.get(split[0]).add(txn);
        }


        for (String name : nameTransactionMap.keySet()) {
            List<Transaction> transactions = nameTransactionMap.get(name);
            transactions.sort(Comparator.comparingInt(t -> t.time));

            for (int i = 0; i < transactions.size() - 1; i++) {
                for (int j = i + 1; j < transactions.size(); j++) {
                    if (!transactions.get(j).city.equals(transactions.get(i).city)) {
                        if (transactions.get(j).time - transactions.get(i).time <= 60) {
                            transactions.get(i).invalid = true;
                            transactions.get(j).invalid = true;
                        } else {
                            break;
                        }
                    }
                }
            }

            for (Transaction txn : transactions) {
                if (txn.invalid) {
                    invalid.add(txn.toString());
                }
            }

        }
        return invalid;
    }

    class Transaction {
        String name;
        int time;
        int amount;
        String city;
        boolean invalid;

        public Transaction(String name, int time, int amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }

        @Override
        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }
    }
}

/*
"bob,820,596,bangkok"
"bob,175,221,amsterdam"
 */