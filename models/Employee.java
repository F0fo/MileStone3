package models;
import java.security.PublicKey;

public class Employee {
    String Name;
    String PhoneNo;
    String Address;

    public Employee(String newName, String newPhone,String newAdd){
        this.Name = newName;
        this.PhoneNo = newPhone;
        this.Address = newAdd;
    }

    public String getName() {
        return Name;
    }
    public String getAddress() {
        return Address;
    }
    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setAddress(String address) {
        this.Address = address;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public void setPhoneNo(String phoneNo) {
       this.PhoneNo = phoneNo;
    }
}
