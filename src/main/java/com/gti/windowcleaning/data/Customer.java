/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 *
 * @author xach
 */
@DatabaseTable(tableName = "customers")
public class Customer extends BaseData {
    @DatabaseField(generatedId = true)
    protected int id;
    @DatabaseField(canBeNull = false)
    protected String name;
    @DatabaseField(canBeNull = false)
    protected String location;
    @DatabaseField(canBeNull = false)
    protected String area;
    @DatabaseField(canBeNull = false)
    protected String contact;
    @DatabaseField(canBeNull = false)
    protected String city;
    @DatabaseField(canBeNull = false)
    protected String phone;
    @DatabaseField(canBeNull = false)
    protected String state;
    @DatabaseField(canBeNull = false)
    protected String addressLine1;
    @DatabaseField()
    protected String addressLine2;
    @DatabaseField(canBeNull = false)
    protected String zip;
    @DatabaseField()
    protected String fax;
    @DatabaseField(canBeNull = false)
    protected String contactB;
    @DatabaseField(canBeNull = false)
    protected String cityB;
    @DatabaseField(canBeNull = false)
    protected String phoneB;
    @DatabaseField(canBeNull = false)
    protected String stateB;
    @DatabaseField(canBeNull = false)
    protected String addressLine1B;
    @DatabaseField()
    protected String addressLine2B;
    @DatabaseField(canBeNull = false)
    protected String zipB;
    @DatabaseField()
    protected String faxB;
    @DatabaseField()
    protected boolean monthly;
    @DatabaseField()
    protected boolean EOM;
    @DatabaseField()
    protected String obtainedBy;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
    protected Date obtainedDate;
    @DatabaseField()
    protected Double monthlyBill;
    @DatabaseField()
    protected Double credit;
    @DatabaseField()
    protected Double deposit;
    @DatabaseField()
    protected Double stateTaxPer;
    @DatabaseField()
    protected boolean active;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
    protected Date inactiveDate;
    @DatabaseField()
    protected String royaltyPaymentTo;
    @DatabaseField()
    protected String notes;
    @DatabaseField()
    protected Double billCharge;
    @DatabaseField()
    protected boolean AWWLine;
    @DatabaseField()
    protected boolean AWWPUR;
    @DatabaseField()
    protected String division;
    @DatabaseField()
    protected String type;
    @DatabaseField()
    protected boolean quarterlyBill;
    @DatabaseField()
    protected String billNotes;
    @DatabaseField()
    protected String company;

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

    @JsonProperty("EOM")
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

    public Double getMonthlyBill() {
        return monthlyBill;
    }

    public void setMonthlyBill(Double monthlyBill) {
        this.monthlyBill = monthlyBill;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getStateTaxPer() {
        return stateTaxPer;
    }

    public void setStateTaxPer(Double stateTaxPer) {
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

    public Double getBillCharge() {
        return billCharge;
    }

    public void setBillCharge(Double billCharge) {
        this.billCharge = billCharge;
    }

    @JsonProperty("AWWLine")
    public boolean isAWWLine() {
        return AWWLine;
    }
    public void setAWWLine(boolean AWWLine) {
        this.AWWLine = AWWLine;
    }

    @JsonProperty("AWWPUR")
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
