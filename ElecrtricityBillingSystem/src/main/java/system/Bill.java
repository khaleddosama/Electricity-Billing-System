package system;

public class Bill {
    private long meterCode;
    private double payment;
    private long paymentID;
    private String region;
    private boolean isPaid;
    public static double currentTariff = 0.5;

    public Bill(long meterCode, double payment, long paymentID, String region, boolean isPaid) {
        this.meterCode = meterCode;
        this.payment = payment;
        this.paymentID = paymentID;
        this.region = region;
        this.isPaid = isPaid;
    }

    public String toFileString() {
        return meterCode + "," + payment + "," + paymentID + "," + region + "," + isPaid;
    }

    @Override
    public String toString() {
        return "ID: " + paymentID + " | Meter: " + meterCode + " | Region: " + region + 
               " | Amount: " + payment + " LE | Status: " + (isPaid ? "PAID" : "UNPAID");
    }

    // Getters & Setters
    public long getMeterCode() { return meterCode; }
    public double getPayment() { return payment; }
    public long getPaymentID() { return paymentID; }
    public String getRegion() { return region; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }
}