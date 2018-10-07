/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 *
 * @author xach
 */
@DatabaseTable(tableName = "customers")
public class Customer {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false)
    private String location;
    @DatabaseField(canBeNull = false)
    private String area;
    @DatabaseField(canBeNull = false)
    private String contact;
    @DatabaseField(canBeNull = false)
    private String city;
    @DatabaseField(canBeNull = false)
    private String phone;
    @DatabaseField(canBeNull = false)
    private String state;
    @DatabaseField(canBeNull = false)
    private String addressLine1;
    @DatabaseField(canBeNull = false)
    private String addressLine2;
    @DatabaseField(canBeNull = false)
    private String zip;
    @DatabaseField()
    private String fax;
    @DatabaseField(canBeNull = false)
    private String contactB;
    @DatabaseField(canBeNull = false)
    private String cityB;
    @DatabaseField(canBeNull = false)
    private String phoneB;
    @DatabaseField(canBeNull = false)
    private String stateB;
    @DatabaseField(canBeNull = false)
    private String addressLine1B;
    @DatabaseField(canBeNull = false)
    private String addressLine2B;
    @DatabaseField(canBeNull = false)
    private String zipB;
    @DatabaseField()
    private String faxB;
    @DatabaseField(canBeNull = false)
    private boolean monthly;
    @DatabaseField(canBeNull = false)
    private boolean EOM;
    @DatabaseField()
    private String obtainedBy;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
    private Date obtainedDate;
    @DatabaseField()
    private double monthlyBill;
    @DatabaseField()
    private double credit;
    @DatabaseField()
    private double deposit;
    @DatabaseField()
    private double stateTaxPer;
    @DatabaseField()
    private boolean active;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
    private Date inactiveDate;
    @DatabaseField()
    private String royaltyPaymentTo;
    @DatabaseField()
    private String notes;
    @DatabaseField()
    private double billCharge;
    @DatabaseField(canBeNull = false)
    private boolean AWWLine;
    @DatabaseField(canBeNull = false)
    private boolean AWWPUR;
    @DatabaseField()
    private String division;
    @DatabaseField(canBeNull = false)
    private String type;
    @DatabaseField()
    private boolean quarterlyBill;
    @DatabaseField()
    private String billNotes;
    @DatabaseField()
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactB() {
        return contactB;
    }

    public void setContactB(String contactB) {
        this.contactB = contactB;
    }

    public String getCityB() {
        return cityB;
    }

    public void setCityB(String cityB) {
        this.cityB = cityB;
    }

    public String getPhoneB() {
        return phoneB;
    }

    public void setPhoneB(String phoneB) {
        this.phoneB = phoneB;
    }

    public String getStateB() {
        return stateB;
    }

    public void setStateB(String stateB) {
        this.stateB = stateB;
    }

    public String getAddressLine1B() {
        return addressLine1B;
    }

    public void setAddressLine1B(String addressLine1B) {
        this.addressLine1B = addressLine1B;
    }

    public String getAddressLine2B() {
        return addressLine2B;
    }

    public void setAddressLine2B(String addressLine2B) {
        this.addressLine2B = addressLine2B;
    }

    public String getZipB() {
        return zipB;
    }

    public void setZipB(String zipB) {
        this.zipB = zipB;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFaxB() {
        return faxB;
    }

    public void setFaxB(String faxB) {
        this.faxB = faxB;
    }
    
}
