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
}
