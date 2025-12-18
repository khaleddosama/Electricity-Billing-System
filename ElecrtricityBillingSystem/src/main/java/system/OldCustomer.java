package system;

import java.util.ArrayList;

public class OldCustomer extends User {

    private long meterCode;
    private int monthlyReading;

    public OldCustomer(int id, String name, String email, String password, long meterCode) {
        super(id, name, email, password, "OldCustomer");
        if (meterCode <= 0) {
            throw new IllegalArgumentException("Invalid meter code");
        }
        this.meterCode = meterCode;
    }

    public String payBill(long meterCodeToPay, double payment) {
        if (payment <= 0) {
            return "Invalid payment amount!";
        }

        for (Bill b : DataStore.bills) {
           
            if (b.getMeterCode() == meterCodeToPay && !b.isPaid()) {
                b.setPaid(true);
                DataStore.saveBills();
                return "Payment successful for Meter: " + meterCodeToPay + "\nPaid: " + payment + " LE";
            }
        }
        return "No unpaid bills found for this Meter Code.";
    }

    public String setMonthlyReading(int monthlyReading) {
        if (monthlyReading < 0) {
            return "Invalid monthly reading! Must be positive.";
        }
        this.monthlyReading = monthlyReading;

        return "Monthly reading updated to: " + monthlyReading;
    }


    public String complaintAboutBill(String message) {
        if (message == null || message.isEmpty()) {
            return "Complaint cannot be empty!";
        }
 
        return "Complaint received: \"" + message + "\"\nWe will review it shortly.";
    }
    
//        public String complaintAboutBill(long meterCode, String message) {
//        try {
//            if (message == null) {
//                throw new IllegalArgumentException("Invalid complaint message");
//            }
//            return "your complaint about the bill with meter code: " + meterCode + " is: " + message
//                    + "\n we will review your complaint, thank you for your feedback";
//        } 
//        catch (IllegalArgumentException e) {
//            return e.getMessage();
//        }
//    }

    public String checkUnpaidMonths(int unpaidMonths) {
        if (unpaidMonths < 0) {
            return "Invalid input.";
        }
        if (unpaidMonths >= 3) {
            return " WARNING: Warning email sent to " + this.email + ". Please pay to avoid service cut.";
        }
        return "You have " + unpaidMonths + " unpaid months. No urgent action needed.";
    }

    public long getMeterCode() {
        return meterCode;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + meterCode;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " | Meter Code: " + meterCode + " | Email: " + email;
    }
}