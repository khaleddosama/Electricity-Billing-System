package system;

public class NewCustomer extends User {
    
    private boolean meterReady;
    private String contractPath;

    public NewCustomer(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.meterReady = false;
    }
    
    // (a) enable customer set all information 
   public void setAllInformation(int id, String name, String email, String password) {
        try {
            setId(id);
            setName(name);
            setEmail(email);
            setPassword(password);
        } 
        catch (Exception e) {
            System.out.println("Invalid details\nplease recheck\n");
        }
    }
    
    public String getAllInformation(){
        return "The details of the customer is : " + "\nID : " + getId() + "\nname : " + 
                getName() + "\nemail : " + getEmail() + "\npassword : " + getPassword() + "\n";
    }
    
    // (b) enable customer attach contract
    public void attachContract(String contractPath) {
        try {
            if (contractPath == null)
                throw new NullPointerException();
            this.contractPath = contractPath;
        }
        catch (Exception e) {
            System.out.println("Contract path cannot be null");
        }
    }
    
    // (c) system will send email (updated)
    public String checkMeterCode() {
        
            meterReady = true;
            return "An email sent to : " + email + " your meter code " + (long)(Math.random()*10_0000 +1)+ " is now ready\n";
       }
     
    // return contract
    public String getContract(){
        if(contractPath != null)
            return contractPath;
        else
            return "Please set contract path\n";
    }    
}
