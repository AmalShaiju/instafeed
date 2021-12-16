package models;


import com.google.android.libraries.places.api.model.Place;

import java.io.Serializable;

public class User implements Serializable {
    private String first;
    private String last;
    private String email;
    private String phone;
    private String address;
    private String organiztionName;
    private String password; // will change to hash
    private UserType userType;


    public User(String first, String last, String email, String phone, String address, String organiztionName,String password, UserType userType) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.organiztionName = organiztionName;
        this.userType = userType;
        this.password = password;
    }

    public String getFullName(){  return first + " " + last; }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getOrganiztionName() {
        return organiztionName;
    }

    public boolean isClaimer(){return userType == UserType.CLAIMER;}

    public void setFirst(String first) {
        if (!U.IsNullOrEmpty(first)) {
            this.first = first;
        } else {
            throw new NullPointerException("first provided is null");
        }
    }

    public void setLast(String last) {
        if (!U.IsNullOrEmpty(last)) {
            this.last = last;
        } else {
            throw new NullPointerException("last provided is null");
        }
    }

    public void setEmail(String email) {
        if (!U.IsNullOrEmpty(email)) {
            this.email = email;
        } else {
            throw new NullPointerException("email provided is null");
        }
    }

    public void setPhone(String phone) {
        if (!U.IsNullOrEmpty(phone)) {
            this.phone = phone;
        } else {
            throw new NullPointerException("phone provided is null");
        }
    }

    public void setAddress(String address) {
        if (!U.IsNullOrEmpty(address)) {
            this.address = address;
        } else {
            throw new NullPointerException("address provided is null");
        }
    }

    public void setOrganiztionName(String organiztionName) {
        if (!U.IsNullOrEmpty(organiztionName)) {
            this.organiztionName = organiztionName;
        } else {
            throw new NullPointerException("organiztionName provided is null");
        }
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String newPassword){
        this.password= newPassword;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object other) {
        return email == ((User) other).email;
    }


}
