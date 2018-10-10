/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.model.MustIncludeException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author xach
 */
public class AddEditCustomerController implements Initializable {
    private int id;
    CustomersModel model = new CustomersModel();

    @FXML
    private TextField name;
    @FXML
    private CheckBox active;
    @FXML
    private DatePicker inactiveSince;
    @FXML
    private CheckBox AWWPur;
    @FXML
    private TextField location;
    @FXML
    private TextField area;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ChoiceBox<String> company;
    @FXML
    private TextField monthlyBill;
    @FXML
    private DatePicker customerSince;
    @FXML
    private TextField obtainedBy;
    @FXML
    private TextField royaltyTo;
    @FXML
    private TextField contact;
    @FXML
    private TextField addressLine1;
    @FXML
    private TextField addressLine2;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zip;
    @FXML
    private TextField fax;
    @FXML
    private TextField phone;
    @FXML
    private TextField contactB;
    @FXML
    private TextField addressLine1B;
    @FXML
    private TextField addressLine2B;
    @FXML
    private TextField cityB;
    @FXML
    private TextField stateB;
    @FXML
    private TextField zipB;
    @FXML
    private TextField faxB;
    @FXML
    private TextField phoneB;
    @FXML
    private TextField billCharge;
    @FXML
    private TextField credit;
    @FXML
    private TextField deposit;
    @FXML
    private TextField salesTax;
    @FXML
    private CheckBox fourWeekInvoice;
    @FXML
    private CheckBox calendarMonthInvoice;
    @FXML
    private CheckBox quarterlyInvoice;
    @FXML
    private TextArea customerNotes;
    @FXML
    private TextArea collectionNotes;

    
    public void setId(int id) {
        this.id = id;
        setupCustomer(id);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupCustomer(id);
    }

    private void setupCustomer(int id){
        if(id < 1) return;
        Customer customer = model.get(id);
        
        name.setText(getString(customer.getName()));
        location.setText(getString(customer.getLocation()));
        area.setText(getString(customer.getArea()));
        AWWPur.setSelected(customer.isAWWPUR());
        active.setSelected(customer.isActive());
        if(customer.getInactiveDate() != null) {
            Date inDate = customer.getInactiveDate();
            inactiveSince.setValue(inDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        type.setValue(customer.getType());
        company.setValue(customer.getCompany());
        monthlyBill.setText(getString(customer.getMonthlyBill()));
        customerSince.setValue(customer.getObtainedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        obtainedBy.setText(getString(customer.getObtainedBy()));
        royaltyTo.setText(getString(customer.getRoyaltyPaymentTo()));
        contact.setText(getString(customer.getContact()));
        addressLine1.setText(getString(customer.getAddressLine1()));
        addressLine2.setText(getString(customer.getAddressLine2()));
        phone.setText(getString(customer.getPhone()));
        fax.setText(getString(customer.getFax()));
        state.setText(getString(customer.getState()));
        city.setText(getString(customer.getCity()));
        zip.setText(getString(customer.getZip()));
        contactB.setText(getString(customer.getContactB()));
        addressLine1B.setText(getString(customer.getAddressLine1B()));
        addressLine2B.setText(getString(customer.getAddressLine2B()));
        phoneB.setText(getString(customer.getPhoneB()));
        faxB.setText(getString(customer.getFaxB()));
        stateB.setText(getString(customer.getStateB()));
        cityB.setText(getString(customer.getCityB()));
        zipB.setText(getString(customer.getZipB()));
        billCharge.setText(getString(customer.getBillCharge()));
        credit.setText(getString(customer.getCredit()));
        deposit.setText(getString(customer.getDeposit()));
        fourWeekInvoice.setSelected(customer.isMonthly());
        quarterlyInvoice.setSelected(customer.isQuarterlyBill());
        customerNotes.setText(getString(customer.getNotes()));
        calendarMonthInvoice.setSelected(customer.isEOM());
        collectionNotes.setText(getString(customer.getBillNotes()));
         
    }

    private Date getDateFromDatePicker(DatePicker dp) {
        LocalDate localDate = dp.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    @FXML
    public void updateCustomer() throws MustIncludeException {
        Customer customer = new Customer();
        customer.setName(name.getText());
        customer.setLocation(location.getText());
        customer.setArea(area.getText());
        customer.setAWWPUR(AWWPur.isSelected());
        customer.setActive(active.isSelected());
        customer.setInactiveDate(getDateFromDatePicker(inactiveSince));
        customer.setType(type.getValue());
        customer.setCompany(company.getValue());
        customer.setMonthlyBill(getDouble(monthlyBill.getText()));
        customer.setObtainedDate(getDateFromDatePicker(customerSince));
        customer.setObtainedBy(obtainedBy.getText());
        customer.setRoyaltyPaymentTo(royaltyTo.getText());
        customer.setContact(contact.getText());
        customer.setAddressLine1(addressLine1.getText());
        customer.setAddressLine2(addressLine2.getText());
        customer.setPhone(phone.getText());
        customer.setFax(fax.getText());
        customer.setState(state.getText());
        customer.setCity(city.getText());
        customer.setZip(zip.getText());
        customer.setContactB(contactB.getText());
        customer.setAddressLine1B(addressLine1B.getText());
        customer.setAddressLine2B(addressLine2B.getText());
        customer.setPhoneB(phoneB.getText());
        customer.setFaxB(faxB.getText());
        customer.setStateB(stateB.getText());
        customer.setCityB(cityB.getText());
        customer.setZipB(zipB.getText());
        customer.setBillCharge(getDouble(billCharge.getText()));
        customer.setCredit(getDouble(credit.getText()));
        customer.setDeposit(getDouble(deposit.getText()));
        customer.setStateTaxPer(getDouble(salesTax.getText()));
        customer.setMonthly(fourWeekInvoice.isSelected());
        customer.setQuarterlyBill(quarterlyInvoice.isSelected());
        customer.setNotes(customerNotes.getText());
        customer.setEOM(calendarMonthInvoice.isSelected());
        customer.setBillNotes(collectionNotes.getText());
        
        model.save(customer);
       
    }

    public CustomersModel getModel() {
        return model; 
    }

    public void setModel(CustomersModel model) {
        this.model = model;
    }

    private Double getDouble(String str) {
        if(str == null || str.trim().equals("")) {
            return null;
        }
        return new Double(str);
    }
  
    private String getString(Double dub) {
        if(dub == null) {
            return "";
        }
        return dub.toString();
    }

    private String getString(String str) {
        if(str == null) {
            return "";
        }
        return str;
    }
}
