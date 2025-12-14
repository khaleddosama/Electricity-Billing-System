
package system;

public class OldCustomer extends User {

    protected long meterCode;
    protected int monthlyreading;
    
    public OldCustomer(int id, String name, String email, String password, long meterCode) {
        super(id, name, email, password);
        if (meterCode <= 0) {
            throw new IllegalArgumentException("Invalid meter code\nPlease recheck\n");
        }
        this.meterCode = meterCode;
    }
    
    
    // (a) enable customer pay with metercode
    public String payBill(long meterCode, double payment) {
        try {
            if (meterCode <= 0 || payment <= 0) {
                throw new IllegalArgumentException("Invalid data\nplease recheck");
            }
            Bill.isPaid=true;
            return "Customer " + name + " paid: " + payment + " using meter code: " + meterCode;
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    

    // (b) enable customer set monthly reading
    public void setMonthlyReading(int monthlyReading) {
        try {
            if (monthlyReading < 0) {
                throw new IllegalArgumentException("Invalid monthly reading\nPlease recheck\n");
            }
            this.monthlyreading = monthlyReading;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // (c) enable customer complain about bill 
    public String complaintAboutBill(long meterCode, String message) {
        try {
            if (message == null) {
                throw new IllegalArgumentException("Invalid complaint message");
            }
            return "your complaint about the bill with meter code: " + meterCode + " is: " + message
                    + "\n we will review your complaint, thank you for your feedback";
        } 
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    // (d) enable customer check unpaid months if more than 2 
    public String checkUnpaidMonths(int unpaidMonths) {
        try {
            if (unpaidMonths < 0) {
                throw new IllegalArgumentException("Invalid unpaid months\nPlease recheck");
            }
            if (unpaidMonths >= 3) {
                return "An email will be sent to: " + email + " due to delay in paying";
            } 
            else{
                return "No urgent bills should be paid";
            }
        } 
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
