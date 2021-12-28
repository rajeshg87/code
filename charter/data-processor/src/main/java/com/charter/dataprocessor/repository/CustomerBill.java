package com.charter.dataprocessor.repository;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("CUSTOMER_BILL_DATA")
public class CustomerBill {

    @Id
    @Column("ID")
    private Integer id;

    @Column("ACCOUNT_NUMBER")
    private Long accountNumber;

    @Column("DIVISION_ID")
    private String divisionId;

    @Column("CUSTOMER_TYPE")
    private String customerType;

    @Column("PHONE_NUMBER")
    private String phoneNumber;

    @Column("EMAIL")
    private String email;

    @Column("SMS_NUMBER")
    private String smsNumber;

    @Column("IVR_NUMBER")
    private String ivrNumber;

    @Column("ADDRESS_1")
    private String addressOne;

    @Column("ADDRESS_2")
    private String addressTwo;

    @Column("CITY")
    private String city;

    @Column("STATE")
    private String state;

    @Column("ZIP")
    private String zip;

    @Column("NAME")
    private String name;

    @Column("DUE_DATE")
    private String dueDate;

    @Column("LST_STMT_BAL")
    private String lastStatementBalance;

    @Column("E_BILL")
    private String eBill;

}

