package com.rajesh.stock.dbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import lombok.Data;

@Entity
@Table(name = "quotes", catalog = "stock")
@Data
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "quote")
    private String quote;

    public UserDetail() {
    }

    public UserDetail(String userName, String quote) {
        this.userName = StringUtils.upperCase(userName);
        this.quote = StringUtils.upperCase(quote);
    }

}
