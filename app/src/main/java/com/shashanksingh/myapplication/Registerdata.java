package com.shashanksingh.myapplication;

public class Registerdata {




    //private variables
    int _id;
    String first_name;
    String last_name;
    String email_id;
    String mobile_number;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    String password;

    // Empty constructor
    public Registerdata(){

    }
    // constructor
    public Registerdata(int id, String first_name, String  last_name,String email_id,String phone_number,String mobile_number){
        this._id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_id=email_id;
        this.mobile_number=mobile_number;
    }


    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    public String getfirstName() {
        // TODO Auto-generated method stub
        return first_name;
    }

    // setting  first name
    public void setfirstName(String first_name){
        this.first_name =first_name;
    }
    public String getlastName() {
        // TODO Auto-generated method stub
        return last_name;
    }

    public void setlastName(String last_name){
        this.last_name =last_name;
    }
    public String getEmailId() {
        // TODO Auto-generated method stub
        return email_id;
    }
    public void setEmailId(String email_id){
        this.email_id =email_id;
    }
    public String getMobNo() {
        // TODO Auto-generated method stub
        return mobile_number;
    }

    public void setMobNo(String mobile_number){
        this.mobile_number=mobile_number;
    }
}



