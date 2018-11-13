package com.gti.windowcleaning.data;

import com.gti.windowcleaning.data.customer.Order;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "invoices")
public class Invoice extends BaseData {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false, foreign = true)
    private Order order;
    @DatabaseField(canBeNull = false)
    private Date dateInvoiced;
    @DatabaseField(canBeNull = false)
    private Date dateRecived;
    @DatabaseField
    private String description;
    @DatabaseField
    private Double amountRecived;
    @DatabaseField(canBeNull = false)
    private Double stateTax;
    @DatabaseField
    private Date writeOffDate;
    @DatabaseField
    private Double writeOffAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDateInvoiced() {
        return dateInvoiced;
    }

    public void setDateInvoiced(Date dateInvoiced) {
        this.dateInvoiced = dateInvoiced;
    }

    public Date getDateRecived() {
        return dateRecived;
    }

    public void setDateRecived(Date dateRecived) {
        this.dateRecived = dateRecived;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmountRecived() {
        return amountRecived;
    }

    public void setAmountRecived(Double amountRecived) {
        this.amountRecived = amountRecived;
    }

    public Double getStateTax() {
        return stateTax;
    }

    public void setStateTax(Double stateTax) {
        this.stateTax = stateTax;
    }

    public Date getWriteOffDate() {
        return writeOffDate;
    }

    public void setWriteOffDate(Date writeOffDate) {
        this.writeOffDate = writeOffDate;
    }

    public Double getWriteOffAmount() {
        return writeOffAmount;
    }

    public void setWriteOffAmount(Double writeOffAmount) {
        this.writeOffAmount = writeOffAmount;
    }
}
