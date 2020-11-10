package com.gurav.samaj.surat.Model;

public class UsersData {
    public String name;
    public String email;
    public String phone;
    public String gender;
    public String address;
    public String amount;
    public String dengi_status;
    public String dengi_type;
    public String lat;
    public String lang;
    public String oname;
    public String dphone;
    public String dname;
    public String did;
    public String cd_amount;
    public String create_date;
    public String update_date;
    public String updated_date;


    public UsersData(String dname, String dphone, String did, String updated_date, String cd_amount, String amount) {
        this.dname = dname;
        this.dphone = dphone;
        this.did = did;
        this.updated_date = updated_date;
        this.cd_amount = cd_amount;
        this.amount = amount;
    }
}
