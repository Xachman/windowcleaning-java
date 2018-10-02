package com.gti.windowcleaning.data;

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
public class Job {
    private int id;
    private int customer_id;
    private String serviced_by;
    private double amount;
    private Date serviceDate;
    private int daysBetween;
    private double percent;
    private boolean printServiceStatement;
    private boolean printInvoice;
    private boolean paymentExpected;
    private String description;
    private int difficulty;
    private String notes;
    private boolean autoPrintServiceStatment;
}
