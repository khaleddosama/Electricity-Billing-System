package system;

public class OldCustomer extends User {

    protected long meterCode;
    protected int monthlyreading;
    protected double amount;

    OldCustomer(int id, String name, String email, String password, long metercode, int monthlyreading) {
        super(id, name, email, password);
        this.meterCode = metercode;
        this.monthlyreading = monthlyreading;
    }

    // (b) enable customer set monthly reading
    public void setMonthlyReading(int monthlyreading) {
        this.monthlyreading = monthlyreading;
    }
    
    // (a) enable customer pay with metercode
    public String payBill(long meterCode, double amount) {
        return "Customer " + name + " paid: " + amount + " using meter code: " + meterCode;
    }

    // (c) enable customer complain about bill 
    public String ComplaintAboutBill(long metercode, String message) {
        return "your complaint about the bill with meter code: " + meterCode + " is: " + message + "\n we will review your complaint, thank you for your feedback";
    }

    // (d) enable customer check unpaid months if more than 2 
    public String checkUnpaidMonths(int unpaidMonths) {
        if (unpaidMonths >= 3) {
            return "An email will be sent to: " + email + " due to delay in paying";
        } else {
            return "No urgent bills should be paid";
        }
    }
}
