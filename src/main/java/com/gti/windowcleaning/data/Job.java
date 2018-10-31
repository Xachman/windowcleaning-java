package com.gti.windowcleaning.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xach
 */
@DatabaseTable(tableName = "jobs")
public class Job {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false, foreign = true)
    private Customer customer;
    @DatabaseField(canBeNull = false)
    private String servicedBy;
    @DatabaseField(canBeNull = false)
    private double amount;
    @DatabaseField(canBeNull = false)
    private Date serviceDate;
    @DatabaseField()
    private int daysBetween;
    @DatabaseField()
    private double percent;
    @DatabaseField(canBeNull = false)
    private boolean printServiceStatement;
    @DatabaseField(canBeNull = false)
    private boolean printInvoice;
    @DatabaseField(canBeNull = false)
    private boolean paymentExpected;
    @DatabaseField()
    private String description;
    @DatabaseField(canBeNull = false)
    private int difficulty;
    @DatabaseField()
    private String notes;
    @DatabaseField(canBeNull = false)
    private boolean autoPrintServiceStatement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getServicedBy() {
        return servicedBy;
    }

    public void setServicedBy(String servicedBy) {
        this.servicedBy = servicedBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getDaysBetween() {
        return daysBetween;
    }

    public void setDaysBetween(int daysBetween) {
        this.daysBetween = daysBetween;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public boolean isPrintServiceStatement() {
        return printServiceStatement;
    }

    public void setPrintServiceStatement(boolean printServiceStatement) {
        this.printServiceStatement = printServiceStatement;
    }

    public boolean isPrintInvoice() {
        return printInvoice;
    }

    public void setPrintInvoice(boolean printInvoice) {
        this.printInvoice = printInvoice;
    }

    public boolean isPaymentExpected() {
        return paymentExpected;
    }

    public void setPaymentExpected(boolean paymentExpected) {
        this.paymentExpected = paymentExpected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isAutoPrintServiceStatement() {
        return autoPrintServiceStatement;
    }

    public void setAutoPrintServiceStatement(boolean autoPrintServiceStatement) {
        this.autoPrintServiceStatement = autoPrintServiceStatement;
    }
}
