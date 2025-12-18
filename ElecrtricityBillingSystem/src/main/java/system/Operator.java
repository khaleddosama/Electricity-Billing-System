package system;

import java.util.*;

public class Operator extends User {

    public Operator(int id, String name, String email, String password) {
        super(id, name, email, password, "Operator");
    }

    public String collectPayment(long billId, double amount) {
        for (Bill b : DataStore.bills) {
            if (b.getPaymentID() == billId && !b.isPaid()) {
                if (amount >= b.getPayment()) {
                    b.setPaid(true);
                    DataStore.saveBills();
                    return "Payment successful! Bill ID: " + billId;
                } else {
                    return "Insufficient amount. Required: " + b.getPayment();
                }
            }
        }
        return "Bill not found or already paid.";
    }

    public String viewRegionBills(String region) {
        StringBuilder result = new StringBuilder("Region: " + region + "\n");
        for (Bill b : DataStore.bills) {
            if (b.getRegion().equalsIgnoreCase(region)) {
                result.append(b.toString()).append("\n");
            }
        }
        return result.toString();
    }

    public String defineTariff(double newTariff) {
        if (newTariff <= 0) {
            return "Invalid Tariff!";
        }
        Bill.currentTariff = newTariff;
        return "Tariff updated to: " + newTariff + " LE/unit.";
    }

    public String cancelSubscription(long meterCode) {
        DataStore.users.removeIf(u -> (u instanceof OldCustomer && ((OldCustomer) u).getMeterCode() == meterCode));
        DataStore.saveUsers();
        return "Subscription for meter " + meterCode + " cancelled.";
    }

    public String printBill(long meterCode, double payment) {
        try {
            return "your bill with metercode " + meterCode
                    + " costs " + payment + " LE\n";
        } catch (Exception e) {
            return "Error while printing bill\nPlease recheck\n";
        }
    }
    
        public String validateReading(int enteredReading, int realReading) {
        try {
            if (enteredReading <= 0 || realReading <= 0) {
                return "Readings cannot be negative or zero.\n";
            }
            if (enteredReading == realReading) {
                return "Reading is valid and matches real consumption.\n";
            }
            else {
                return "Real reading and entered reading are not the same...Please re-check.\n";
            }
        } 
        catch (Exception e) {
            return "Error while validating reading\nPlease recheck\n";
        }
    }

    
//    public String viewRegion(String region) {
//        try {
//            Random units = new Random(2);
//            int numberOfUnits = units.nextInt(100) + 1;
//
//            String display = "-------you are viewing the bills of region:"
//                    + region + "--------\n";
//            String result = "";
//
//            for (int i = 0; i < numberOfUnits; i++) {
//                result += "Bill of apartment " + (i + 1)
//                        + " with metercode " + ((long) (Math.random() * 100_000) + 1)
//                        + " paid " + ((float) (Math.random() * 1000) + 1)
//                        + " LE\n";
//            }
//
//            return display + result;
//        } catch (Exception e) {
//            return "Error while viewing region bills\nPlease recheck\n";
//        }
//    }
}
