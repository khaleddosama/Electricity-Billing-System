package system;
import java.util.*;
import java.io.*;

public class DataStore {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Bill> bills = new ArrayList<>();
    
    private static final String USERS_FILE = "users_data.txt";
    private static final String BILLS_FILE = "bills_data.txt";

    public static void loadUsers() {
        users.clear();
        File file = new File(USERS_FILE);
        if (!file.exists() || file.length() == 0) {
            users.add(new Admin(1, "Admin", "admin@mail.com", "123")); // Default Admin
            saveUsers();
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) continue;
                String[] p = line.split(",");
                int id = Integer.parseInt(p[0]);
                String name = p[1], email = p[2], pass = p[3], role = p[4];

                switch (role) {
                    case "Admin": users.add(new Admin(id, name, email, pass)); break;
                    case "Operator": users.add(new Operator(id, name, email, pass)); break;
                    case "OldCustomer": 
                        users.add(new OldCustomer(id, name, email, pass, Long.parseLong(p[5]))); 
                        break;
                    case "NewCustomer":
                        NewCustomer nc = new NewCustomer(id, name, email, pass);
                        // p[5] -> meterCode, p[6] -> contractPath, p[7] -> meterReady
                        nc.checkMeterCode(); 
                        users.add(nc);
                        break;
                }
            }
        } catch (Exception e) { System.out.println("Error loading users: " + e.getMessage()); }
    }

    public static void saveUsers() {
        try (PrintWriter out = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) out.println(u.toFileString());
        } catch (IOException e) { System.out.println("Error saving users."); }
    }

    public static void loadBills() {
        bills.clear();
        File file = new File(BILLS_FILE);
        if (!file.exists()) return;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(",");
                bills.add(new Bill(Long.parseLong(p[0]), Double.parseDouble(p[1]), 
                          Long.parseLong(p[2]), p[3], Boolean.parseBoolean(p[4])));
            }
        } catch (Exception e) { System.out.println("Error loading bills."); }
    }

    public static void saveBills() {
        try (PrintWriter out = new PrintWriter(new FileWriter(BILLS_FILE))) {
            for (Bill b : bills) out.println(b.toFileString());
        } catch (IOException e) { System.out.println("Error saving bills."); }
    }

}