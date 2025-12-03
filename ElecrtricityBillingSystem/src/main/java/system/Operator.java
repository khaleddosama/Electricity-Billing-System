package system;

public class Operator extends User {

    private double payment;
    private long paymentID;
    private double currentTariff = 1.0;
    private final String[] regions = {"Masr-ELGedida", "Nasr-City", "Ain-Shams", "Helmya"};

    public Operator() {
    }

    // (a) collect payment from customer
    public String collectPayment(long paymentID, double payment) {
        if (paymentID > 0) {
            return "payment with " + payment + "using " + paymentID + "succesfully";
        } else {
            return "payment unsuccesfully \n please make sure from details";
        }
    }

    // (b) print the bill details
    public String printBill(long meterCode) {
        return "your bill with metercode " + meterCode + "costs" + Math.random() * 1000;
    }

    // (c) enable operator see bills 
    public String viewRegion(String region) {

        if (region.equals(regions[0])
                || region.equals(regions[1])
                || region.equals(regions[2])
                || region.equals(regions[3])) {

            String result = "";

            for (int i = 0; i < 10; i++) {
                result += "Bill of apartment " + (i + 1)
                        + " with metercode " + (long) (Math.random() * 100_000)
                        + " paid " + (Math.random() * 1000)
                        + "\n";
            }

            return result;
        }

        return "Sorry this region is uncovered by you\n";
    }

    
    // (d) validate reading with real consumption
    public String validateReading(int enteredReading, int realReading) {
        if (enteredReading < 0 || realReading < 0) {
            return "Readings cannot be negative.\n";
        }
        if (enteredReading == realReading) {
            return "Reading is valid and matches real consumption.\n";
        } else {
            return"Real reading and entered reading are not the same...Please re-check.\n";
        }
    }
    
     // (e) define tariff for customer
    public String defineTariffForCustomer(long meterCode, double tariff) {
        if (meterCode <= 0 || tariff <= 0) {
            return "Invalid details.\n";
        }
        
        this.currentTariff = tariff;
        return "Tariff for meter code " + meterCode +
               " has been set to " + tariff + " per kWh.\n";
    }
    
    // (f) stop meter and cancel subscription
    public String cancelSubscription(long meterCode) {
        if (meterCode <= 0) {
            return "Invalid meter code.";
        }
     
        return "Meter code : " + meterCode +
        " has been stopped and subscription cancelled.\n";
    }
}
