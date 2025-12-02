
package system;

public class Operator extends User {
    private double payment;
    private long paymentID;
    private final String []regions = {"Masr-ELGedida" , "Nasr-City" ,"Ain-Shams" , "Helmya"};
    
    public Operator (){  
    }
    
    // (a) collect payment from customer
    public String collectPayment(long paymentID, double payment){
        if(paymentID > 0)
            return "payment with " + payment + "using " + paymentID + "succesfully";
        else
            return"payment unsuccesfully \n please make sure from details";
    }
    
    // (b) print the bill details
    public String printBill(long meterCode){
        return "your bill with metercode " + meterCode + "costs" + Math.random() * 1000;
    }
    
    // (c) 
//    public String viewRegion(String region){
//        if(region == regions[0] | region == regions[1] | region == regions[2] | region == regions[3])
//            return 
//        
//    }
//    
    
    
    
}
