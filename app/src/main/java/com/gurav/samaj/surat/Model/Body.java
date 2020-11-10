package com.gurav.samaj.surat.Model;

public class Body {
    public String address;
    public String amount;
    public String name;
    public String odha_name;
    public String phone;

    public Body(String odha_name2, String name2, String phone2, String address2, String amount2) {
        this.odha_name = odha_name2;
        this.name = name2;
        this.phone = phone2;
        this.address = address2;
        this.amount = amount2;
    }
}
