// 929: https://leetcode.com/problems/unique-email-addresses/
public class UniqueEmails {
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();

        for(String email : emails) {
            String[] split = email.split("@");
            String name = split[0];
            String domain = split[1];

            String[] nameSplit = name.split("\\+");
            String first = nameSplit[0].replace(".","");

            uniqueEmails.add(first + "@" + domain);

        }

        return uniqueEmails.size();
    }
}
