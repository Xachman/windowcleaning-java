/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import com.gti.windowcleaning.data.annotations.Validate;
import com.gti.windowcleaning.data.customer.Contact;
import java.util.Date;

/**
 *
 * @author xach
 */
public class Customer {
    private int id;
    @Validate
    private String name;
    @Validate
    private String location;
    @Validate
    private String area;
    @Validate
    private Contact contact;
    @Validate
    private Contact contactBilling;
    @Validate
    private boolean monthly;
    @Validate
    private boolean EOM;
    private String obtainedBy;
    private Date obtainedDate;
    private double monthlyBill;
    private double credit;
    private double deposit;
    private double stateTaxPer;
    private boolean active;
    private Date inactiveDate;
    private String royaltyPaymentTo;
    private String notes;
    private double billCharge;
    @Validate
    private boolean AWWLine;
    @Validate
    private boolean AWWPUR;
    private String division;
    @Validate
    private String type;
    private boolean quarterlyBill;
    private String billNotes;
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContactBilling() {
        return contactBilling;
    }

    public void setContactBilling(Contact contactBilling) {
        this.contactBilling = contactBilling;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public boolean isEOM() {
        return EOM;
    }

    public void setEOM(boolean EOM) {
        this.EOM = EOM;
    }

    public String getObtainedBy() {
        return obtainedBy;
    }

    public void setObtainedBy(String obtainedBy) {
        this.obtainedBy = obtainedBy;
    }

    public Date getObtainedDate() {
        return obtainedDate;
    }

    public void setObtainedDate(Date obtainedDate) {
        this.obtainedDate = obtainedDate;
    }

    public double getMonthlyBill() {
        return monthlyBill;
    }

    public void setMonthlyBill(double monthlyBill) {
        this.monthlyBill = monthlyBill;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getStateTaxPer() {
        return stateTaxPer;
    }

    public void setStateTaxPer(double stateTaxPer) {
        this.stateTaxPer = stateTaxPer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getRoyaltyPaymentTo() {
        return royaltyPaymentTo;
    }

    public void setRoyaltyPaymentTo(String royaltyPaymentTo) {
        this.royaltyPaymentTo = royaltyPaymentTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getBillCharge() {
        return billCharge;
    }

    public void setBillCharge(double billCharge) {
        this.billCharge = billCharge;
    }

    public boolean isAWWLine() {
        return AWWLine;
    }

    public void setAWWLine(boolean AWWLine) {
        this.AWWLine = AWWLine;
    }

    public boolean isAWWPUR() {
        return AWWPUR;
    }

    public void setAWWPUR(boolean AWWPUR) {
        this.AWWPUR = AWWPUR;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isQuarterlyBill() {
        return quarterlyBill;
    }

    public void setQuarterlyBill(boolean quarterlyBill) {
        this.quarterlyBill = quarterlyBill;
    }

    public String getBillNotes() {
        return billNotes;
    }

    public void setBillNotes(String billNotes) {
        this.billNotes = billNotes;
    }
    
}
