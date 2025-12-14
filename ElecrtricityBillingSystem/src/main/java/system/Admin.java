package system;

import java.util.*;

public class Admin  {
   

    public Admin() {
    }

    // (A) View all bills of specific regions
    public String viewBillsByRegion(String region) {
        try {
            // (updated)
            Random units = new Random(2);
            int numberOfUnits = units.nextInt(100) + 1;

            String result = "";
            String display = "-------you are viewing the bills of region:" + region + "--------\n";

            for (int j = 0; j < 4; j++) {

                result += "\n --- Month " + (j + 1) + " ---\n";

                for (int i = 0; i < numberOfUnits; i++) {
                    result += "Bill of apartment " + (i + 1)
                            + " with metercode " + ((long) (Math.random() * 100_000) + 1)
                            + " paid " + ((Math.random() * 1000) + 1)
                            + " LE\n";
                }
            }
            return display + result;

        } catch (Exception e) {
            return "Error while viewing bills by region\nplease recheckPlease recheck";
        }
    }

    // (B) View total collected (Only Paid Bills)
    public String viewTotalCollected(ArrayList<Bill> allBills) {
        try {
            double total = 0;

            for (Bill bill : allBills) {
                if (bill.isPaid()) {
                    total += bill.getPayment();
                }
            }
            return "--- Admin: Total Collected Revenue ---\n"
                    + "Total collected : " + total;

        } catch (Exception e) {
            return "Error while calculating total collected revenue\nPlease recheck";
        }
    }

    // (C) Consumption statistics for specific region
    public String reportConsumptionStats(String region, ArrayList<Bill> allBills) {
        try {
            double sum = 0;
            int size = 0;

            for (Bill bill : allBills) {
                if (bill.isPaid()) {
                    sum += bill.getPayment();
                    size++;
                }
            }

            return "Region : " + region
                    + "\nNumber of units is : " + size
                    + "\nAverage of total collected = " + (sum / size) + "\n";

        } catch (Exception e) {
            return "Error while generating consumption statistics\nPlease recheck";
        }
    }

    // (D) Manage Users (Add / Update / Delete)
    // 1. Add
    public String addUser(ArrayList<User> users, User newUser) {
        try {
            users.add(newUser);
            return "Admin: User " + newUser.getName() + " added.\n";
        } catch (Exception e) {
            return "Error while adding user\nPlease recheck\n";
        }
    }

    // 2. Delete
    public String deleteUser(ArrayList<User> users, int userId) {
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == userId) {
                    users.remove(i);
                    return "User with user ID : " + userId + " deleted successfully\n";
                }
            }
            return "User with user ID " + userId + " is not found\n";

        } catch (Exception e) {
            return "Error while deleting user\nPlease recheck\n";
        }
    }

    // 3. Update (Email for example)
    public String updateUserEmail(ArrayList<User> users, int userId, String newEmail) {
        try {
            for (User u : users) {
                if (u.getId() == userId) {
                    u.setEmail(newEmail);
                    return "Admin: Email updated for " + u.getName();
                }
            }
            return "Admin: User not found to update.";

        } catch (Exception e) {
            return "Error while updating user email\nPlease recheck\n";
        }
    }
}
