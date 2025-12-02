package system;

public class NewCustomer extends User {
    
    private boolean meterReady;
    private String contractPath;

    NewCustomer(boolean meterReady,int id, String name, String email, String password) {
        super(id, name, email, password);
        this.meterReady = meterReady;
    }
    
    // (a) enable customer set all information 
    public void setAllInformation(int id, String name, String email, String password) {
        setId(id);
        setName(name);
        setEmail(email);
        setPassword(password);
    }
    
    // (c) system will send email 
    public String checkMeterCode(long metercode) {
        if(!meterReady){
            meterReady = true;
            return "an email sent to:" + email + "your meter code " + metercode + " is now ready";
        }        
        else
            return "An email is already sent with your metercode";
    }
    
    // (b) enable customer attach contract
    public void attachContract(String contractPath){
        this.contractPath = contractPath;
    }
    
    // return contract
    public String getContract(){
        return contractPath;
    }
    
    
    
    
    
    
    
    
}
