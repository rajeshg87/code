package com.charter.dataprocessor;

import lombok.Data;

@Data
public class TestData {

    private Long billingAccountNumber;
    private String spcDivisionId;
    private String customerType;
    private String customerNumber;
    private String email;
    private String smsPhoneNumber;
    private String ivrPhoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String customerName;
    private String dueDate;
    private String lastStatementBalance;
    private String eBillSubscribed;
    private String startingInstallment;

    @Override
    public String toString() {
        return billingAccountNumber +
                "|" + spcDivisionId +
                "|" + customerType +
                "|" + customerNumber +
                "|'" + email +
                "|" + smsPhoneNumber +
                "|" + ivrPhoneNumber +
                "|" + addressLine1 +
                "|" + addressLine2 +
                "|" + city +
                "|" + state +
                "|" + zip +
                "|" + customerName +
                "|" + dueDate +
                "|" + lastStatementBalance +
                "|" + eBillSubscribed +
                "|" + startingInstallment;
    }
}
