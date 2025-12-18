package system;
import java.util.*;

public class Admin extends User {

    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password, "Admin");
    }

    public String viewBillsByRegion(String region) {
    String result = "------- Bills of region: " + region + " --------\n";
    boolean found = false;

    for (Bill b : DataStore.bills) {

        if (b.getRegion().equalsIgnoreCase(region)) {
            result += b.toString() + "\n----------------\n";
            found = true;
        }
    }

    if (found) {
        return result;
    } else {
        return "No bills found in this region.";
    }
}


    public String viewTotalCollected() {
        double total = 0;
        for (Bill b : DataStore.bills) {
            if (b.isPaid()) {
                total += b.getPayment();
            }
        }
        return "--- Admin Report ---\nTotal Collected Revenue: " + total + " LE";
    }

    public String reportConsumptionStats(String region) {
        double sum = 0;
        int count = 0;
        for (Bill b : DataStore.bills) {
            if (b.getRegion().equalsIgnoreCase(region) && b.isPaid()) {
                sum += b.getPayment();
                count++;
            }
        }
        if (count == 0) return "No data for region: " + region;
        return "Region: " + region + "\nUnits: " + count + "\nAverage Payment: " + (sum / count) + " LE";
    }

   
    public String addUser(User newUser) {
        DataStore.users.add(newUser);
        DataStore.saveUsers();
        return "User [" + newUser.getName() + "] added successfully.";
    }


   public String deleteUser(int userId) {

    for (int i = 0; i < DataStore.users.size(); i++) {
        

        if (DataStore.users.get(i).getId() == userId) {
            
            DataStore.users.remove(i); 
            DataStore.saveUsers();     
            
            return "User ID " + userId + " deleted successfully.";
        }
    }
    

    return "User not found.";
}
   
   
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
   
//       public String viewBillsByRegion(String region) {
//        try {
//            // (updated)
//            Random units = new Random(2);
//            int numberOfUnits = units.nextInt(100) + 1;
//
//            String result = "";
//            String display = "-------you are viewing the bills of region:" + region + "--------\n";
//
//            for (int j = 0; j < 4; j++) {
//
//                result += "\n --- Month " + (j + 1) + " ---\n";
//
//                for (int i = 0; i < numberOfUnits; i++) {
//                    result += "Bill of apartment " + (i + 1)
//                            + " with metercode " + ((long) (Math.random() * 100_000) + 1)
//                            + " paid " + ((Math.random() * 1000) + 1)
//                            + " LE\n";
//                }
//            }
//            return display + result;
//
//        } catch (Exception e) {
//            return "Error while viewing bills by region\nplease recheckPlease recheck";
//        }
//    }
   
}