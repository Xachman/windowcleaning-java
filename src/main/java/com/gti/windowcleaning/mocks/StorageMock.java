/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.mocks;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.data.customer.Contact;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xach
 */
public class StorageMock implements StorageI {

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            String fileName = getClass().getResource("/mock/data/customers.json").getFile();
            FileReader r = new FileReader(fileName);
            Object obj = parser.parse(r);
            JSONArray jCustomers = (JSONArray) obj;
            
            Iterator<JSONObject> iter = jCustomers.iterator();
        SimpleDateFormat sdf = new SimpleDateFormat("m/d/yyyy");
            while(iter.hasNext()) {
                JSONObject jobj = iter.next();
                Customer customer = new Customer();

                Contact contact = new Contact();
                String contactName = jobj.get("contact").toString();
                String addressLine1 = jobj.get("addressLine1").toString();
                String addressLine2 = jobj.get("addressLine2").toString();
                String city = jobj.get("city").toString();
                String state = jobj.get("state").toString();
                String zip = jobj.get("zip").toString();
                String phone = jobj.get("phone").toString();
                String fax = jobj.get("fax").toString();
                contact.setName(contactName);
                contact.setAddressLine1(addressLine1);
                contact.setAddressLine2(addressLine2);
                contact.setCity(city);
                contact.setState(state);
                contact.setPhone(phone);
                contact.setZip(zip);
                contact.setFax(fax);
                
                Contact contactb = new Contact();
                String contactName_b = jobj.get("contact_b").toString();
                String addressLine1_b = jobj.get("addressLine1_b").toString();
                String addressLine2_b = jobj.get("addressLine2_b").toString();
                String city_b = jobj.get("city_b").toString();
                String state_b = jobj.get("state_b").toString();
                String zip_b = jobj.get("zip_b").toString();
                String phone_b = jobj.get("phone_b").toString();
                String fax_b = jobj.get("fax_b").toString();
                contactb.setName(contactName_b);
                contactb.setAddressLine1(addressLine1_b);
                contactb.setAddressLine2(addressLine2_b);
                contactb.setCity(city_b);
                contactb.setState(state_b);
                contactb.setPhone(phone_b);
                contactb.setZip(zip_b);
                contactb.setFax(fax_b);
                
                int id = new Integer(jobj.get("id").toString());
                String name = jobj.get("name").toString();
                String location = jobj.get("location").toString();
                String area = jobj.get("area").toString();
                boolean monthly = parseBoolean(jobj.get("monthly"));
                boolean EOM = parseBoolean(jobj.get("EOM"));
                String obtainedBy = jobj.get("obtainedBy").toString();
                String obtainedDate = jobj.get("obtainedDate").toString();
                double montlyBill = parseDouble(jobj.get("montlyBill"));
                double credit = parseDouble(jobj.get("credit"));
                double deposit = parseDouble(jobj.get("deposit"));
                double stateTaxPer = parseDouble(jobj.get("stateTaxPer"));
                boolean active = parseBoolean(jobj.get("active"));
                String inactiveDate = parseString(jobj.get("inactiveDate"));
                String royaltyPayment = parseString(jobj.get("royaltyPayment"));
                String notes = parseString(jobj.get("notes"));
                double billCharge = parseDouble(jobj.get("billCharge"));
                boolean AWWLine = parseBoolean(jobj.get("AWWLine"));
                boolean AWWPur = parseBoolean(jobj.get("AWWPur"));
                String division = parseString(jobj.get("division"));
                String type = parseString(jobj.get("type"));
                String company = parseString(jobj.get("company"));
                boolean quarterlyBill = parseBoolean(jobj.get("quarterlyBill"));
                String billNotes = parseString(jobj.get("billNotes"));
               

                customer.setId(id);
                customer.setName(name);
                customer.setContact(contact);
                customer.setContactBilling(contact);
                customer.setLocation(location);
                customer.setArea(area);
                customer.setMonthly(monthly);
                customer.setEOM(EOM);
                customer.setObtainedBy(obtainedBy);
                customer.setObtainedDate(sdf.parse(obtainedDate));
                customer.setMonthlyBill(montlyBill);
                customer.setCredit(credit);
                customer.setDeposit(deposit);
                customer.setStateTaxPer(stateTaxPer);
                customer.setActive(active);
                if(!inactiveDate.equals("")) {
                    customer.setInactiveDate(sdf.parse(inactiveDate));
                }
                customer.setRoyaltyPaymentTo(royaltyPayment);
                customer.setNotes(notes);
                customer.setBillCharge(billCharge);
                customer.setAWWLine(AWWLine);
                customer.setAWWPUR(AWWPur);
                customer.setDivision(division);
                customer.setType(type);
                customer.setCompany(company);
                customer.setQuarterlyBill(quarterlyBill);
                customer.setBillNotes(billNotes);

                customers.add(customer);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    @Override
    public Customer getCustomer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();
        for(Customer customer: getCustomers()) {
            if(customer.getName().contains(name)) {
                customers.add(customer);
            }
        }
        return customers;
    }

    private double parseDouble(Object d) {
       if(d == null) return 0.00;
       
       return new Double(d.toString()); 
    }

    private boolean parseBoolean(Object b) {
        if(b == null) return false;
        return (boolean) b;
    }

    private String parseString(Object s) {
        if(s == null) return "";
        return s.toString();
    }
    
}
