// https://leetcode.com/problems/accounts-merge/description/

import java.util.*;

/* Brute force works as follows:
    - For each account, iterate through all its emails and map each email to the list of accounts 
    that contain it. This helps in quickly finding all accounts that share a particular email.
    - Perform DFS for merging
        - Start with each account and use DFS to find all connected accounts. 
        Two accounts are considered connected if they share at least one email.
        - Collect all emails from the connected accounts during the DFS traversal.
        - Mark accounts as visited to avoid processing the same account multiple times.
    - Form merged accounts: For each set of connected accounts found in the DFS, collect all
     unique emails, sort them, and form a merged account with the account holder's name.
     - Repeat the DFS process for each account, ensuring that all accounts are visited and processed.
 */
public class AccountMergerBruteForce {

    // Merge accounts with common emails
    public List<List<String>> mergeAccounts(List<List<String>> accounts) {
        // Map to store email to list of accounts mapping
        Map<String, List<List<String>>> emailToAccount = new HashMap<>();
        
        // Step 1: Create email to account mapping
        for (List<String> account : accounts) {
            List<String> emails = account.subList(1, account.size()); // Get the list of emails
            for (String email : emails) {
                emailToAccount.putIfAbsent(email, new ArrayList<>()); // Initialize list if email is not present
                emailToAccount.get(email).add(account); // Add the account to the list for this email
            }
        }
        
        List<List<String>> mergedAccounts = new ArrayList<>(); // List to store the merged accounts
        Set<List<String>> visitedAccounts = new HashSet<>(); // Set to track visited accounts
        
        // Step 2: Perform DFS to merge accounts with common emails
        for (List<String> account : accounts) {
            if (visitedAccounts.contains(account)) {
                continue; // Skip if the account has already been visited
            }
            Set<String> emails = new HashSet<>(); // Set to collect all unique emails
            dfs(account, visitedAccounts, emails, emailToAccount); // Perform DFS to gather all connected emails
            List<String> mergedAccount = new ArrayList<>(); // List to store the merged account
            mergedAccount.add(account.get(0)); // Add the name of the account holder
            List<String> sortedEmails = new ArrayList<>(emails); // Convert the set of emails to a list
            Collections.sort(sortedEmails); // Sort the emails
            mergedAccount.addAll(sortedEmails); // Add the sorted emails to the merged account
            mergedAccounts.add(mergedAccount); // Add the merged account to the result list
        }
        
        return mergedAccounts;
    }
    
    // Helper method to perform DFS and collect all connected emails
    private void dfs(List<String> account, Set<List<String>> visitedAccounts, Set<String> emails, Map<String, List<List<String>>> emailToAccount) {
        if (visitedAccounts.contains(account)) {
            return; // Return if the account has already been visited
        }
        visitedAccounts.add(account); // Mark the account as visited
        emails.addAll(account.subList(1, account.size())); // Add all emails of the current account to the set
        for (String email : account.subList(1, account.size())) { // Iterate through each email of the current account
            for (List<String> nextAccount : emailToAccount.get(email)) { // Iterate through each account associated with the email
                dfs(nextAccount, visitedAccounts, emails, emailToAccount); // Recursively perform DFS
            }
        }
    }
}

/* Optimized solution with union find

    Works as follows:
        - Initialize a Union-Find (Disjoint Set Union) data structure to manage email 
        connections, and map each email to the account holder's name.
        - For each account, union all its emails. This effectively groups emails together 
        if they belong to the same account. The union operation connects the emails by ensuring 
        they share the same parent or representative email.
        - Use the find operation to determine the root parent email for each email.
        - Group all emails that share the same root parent. These emails are connected 
        and thus belong to the same person.
        - For each root parent email, collect all emails in the group, sort them, and 
        form a merged account with the account holder's name.
*/

class Solution {
    class UnionFind {
        private Map<String, String> parent;  // Map to store the parent of each email
        private Map<String, Integer> rank;  // Map to store the rank of each email's tree

        public UnionFind() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        // Find the root of the email, with path compression
        public String find(String email) {
            if (!parent.containsKey(email)) {
                parent.put(email, email);
                rank.put(email, 0);
            }
            if (!email.equals(parent.get(email))) {
                parent.put(email, find(parent.get(email)));  // Path compression
            }
            return parent.get(email);
        }

        // Union two sets by rank
        public void union(String email1, String email2) {
            String root1 = find(email1);
            String root2 = find(email2);

            if (!root1.equals(root2)) {
                int rank1 = rank.get(root1);
                int rank2 = rank.get(root2);

                if (rank1 > rank2) {
                    parent.put(root2, root1);
                } else if (rank1 < rank2) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root1, root2);
                    rank.put(root2, rank2 + 1);
                }
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind uf = new UnionFind();
        Map<String, String> emailToName = new HashMap<>();

        // Union emails in the same account
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name); // Map each email to the account holder's name
                uf.union(email, account.get(1)); // Union this email with the first email in the account
            }
        }

        // Group emails by their root parent
        Map<String, TreeSet<String>> rootToEmails = new HashMap<>();
        for (String email : emailToName.keySet()) {
            // Find the root email for the current email
            String root = uf.find(email);
            // If the root email is not already a key in the map, create a new TreeSet for it
            if (!rootToEmails.containsKey(root)) {
                rootToEmails.put(root, new TreeSet<>());
            }
            // Add the current email to the set of emails for the root email
            rootToEmails.get(root).add(email);
        }

        /// Prepare the result in the required format
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (String root : rootToEmails.keySet()) {
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(emailToName.get(root));
            mergedAccount.addAll(rootToEmails.get(root));
            mergedAccounts.add(mergedAccount);
        }

        return mergedAccounts;
    }
}
