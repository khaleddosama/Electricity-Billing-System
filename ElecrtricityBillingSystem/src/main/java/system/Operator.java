package system;

public class Operator {

    public Operator() {
    }

    // (a) collect payment from customer
    public String collectPayment(long paymentID, double payment) {
        try {
            if (paymentID > 0 && payment > 0) {
                // updated
                Bill.isPaid = true;
                return "payment with " + payment + " LE using ID "
                        + paymentID + " succesfully\n";
            } 
            else {
                return "payment unsuccesfully \nplease make sure from details\n";
            }
        }
        catch (Exception e) {
            return "Error while collecting payment\nPlease recheck\n";
        }
    }

    // (b) print the bill details
    public String printBill(long meterCode, double payment) {
        try {
            return "your bill with metercode " + meterCode
                    + " costs " + payment + " LE\n";
        }
        catch (Exception e) {
            return "Error while printing bill\nPlease recheck\n";
        }
    }

    // (c) enable operator see bills
    public String viewRegion(String region) {
        try {
            String display = "-------you are viewing the bills of region:"
                    + region + "--------\n";
            String result = "";

            for (int i = 0; i < (int) (Math.random() * 100) + 1; i++) {
                result += "Bill of apartment " + (i + 1)
                        + " with metercode " + ((long) (Math.random() * 100_000) + 1)
                        + " paid " + ((float) (Math.random() * 1000) + 1)
                        + " LE\n";
            }

            return display + result;
        }
        catch (Exception e) {
            return "Error while viewing region bills\nPlease recheck\n";
        }
    }

    // (d) validate reading with real consumption
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

    // (e) define tariff for customer
    public String defineTariffForCustomer(long meterCode, double tariff) {
        try {
            if (meterCode <= 0 || tariff <= 0) {
                return "Invalid details.\n";
            }

            Bill.currentTariff = tariff;
            return "Tariff for meter code " + meterCode
                    + " has been set to " + tariff + " per kWh.\n";
        } 
        catch (Exception e) {
            return "Error while defining tariff\nPlease recheck\n";
        }
    }

    // (f) stop meter and cancel subscription
    public String cancelSubscription(long meterCode) {
        try {
            if (meterCode <= 0) {
                return "Invalid meter code.\n";
            }

            return "Meter code : " + meterCode
                    + " has been stopped and subscription cancelled.\n";
        }
        catch (Exception e) {
            return "Error while cancelling subscription\nPlease recheck\n";
        }
    }
}