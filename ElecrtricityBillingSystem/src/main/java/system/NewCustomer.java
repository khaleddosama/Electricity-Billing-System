package system;

import java.io.*;

public class NewCustomer extends User {

    private boolean meterReady;
    private String contractPath;
    private long meterCode;


    public NewCustomer(int id, String name, String email, String password) {
        super(id, name, email, password, "NewCustomer");
        this.meterReady = false; 
        this.contractPath = "No contract attached";
        this.meterCode = 0;
    }


    public String getAllInformation() {
        return "New Customer Details:\n" +
               "-----------------------\n" +
               "ID: " + getId() +
               "\nName: " + getName() +
               "\nEmail: " + getEmail() +
               "\nMeter Ready: " + (meterReady ? "Yes " : "No ") +
               "\nMeter Code: " + (meterCode == 0 ? "N/A" : meterCode) +
               "\nContract: " + contractPath;
    }


    public void attachContract(String contractPath) {
        if (contractPath == null || contractPath.isEmpty()) {
            throw new IllegalArgumentException("Contract path cannot be empty!");
        }
        this.contractPath = contractPath;
        System.out.println("Contract attached successfully for user: " + name);

        DataStore.saveUsers();
    }


    public long checkMeterCode() {
        if (meterCode == 0) {
            this.meterCode = (long) (Math.random() * 100000 + 1);
        }
        this.meterReady = true;
        System.out.println("Meter has been assigned and is ready.");
        
        DataStore.saveUsers();
        return meterCode;
    }
    
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

    // (D) Getters & Setters
    public boolean isMeterReady() {
        return meterReady;
    }

    public long getMeterCode() {
        if (!meterReady) {
            System.out.println("Warning: Meter is not ready yet!");
        }
        return meterCode;
    }

    public String getContract() {
        return contractPath;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + meterCode + "," + contractPath + "," + meterReady;
    }

    @Override
    public String toString() {
        return "New Customer: " + name + " | Status: " + (meterReady ? "Meter Ready" : "Pending");
    }
}