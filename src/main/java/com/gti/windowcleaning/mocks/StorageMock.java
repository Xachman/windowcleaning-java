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
import java.util.ArrayList;
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
                boolean monthly = (boolean) jobj.get("monthly");
                boolean EOM = (boolean) jobj.get("EOM");
                String obtainedBy = jobj.get("obtainedBy").toString();
                String optainedDate = jobj.get("obtainedDate").toString();
                String montlyBill = jobj.get("montlyBill").toString();
                String credit = jobj.get("credit").toString();
                String deposit = jobj.get("deposit").toString();
                String stateTaxPer = jobj.get("stateTaxPer").toString();
                String active = jobj.get("active").toString();
                String inactiveDate = jobj.get("inactiveDate").toString();
                String royaltyPayment = jobj.get("royaltyPayment").toString();
                String notes = jobj.get("notes").toString();
                String billCharge = jobj.get("billCharge").toString();
                String AWWLine = jobj.get("AWWLine").toString();
                String AWWPur = jobj.get("AWWPur").toString();
                String division = jobj.get("division").toString();
                String type = jobj.get("type").toString();
                String company = jobj.get("company").toString();
                String quarterlyBill = jobj.get("quarterlyBill").toString();
                String billNotes = jobj.get("billNotes").toString();
               

                customer.setId(id);
                customer.setName(name);
                customer.setContact(contact);
                customer.setContactBilling(contact);
                customer.setLocation(location);
                customer.setArea(area);
                customer.setMonthly(monthly);
                customer.setEOM(EOM);
                customer.setObtainedBy(obtainedBy);
                customer.setObtainedDate(obtainedBy);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
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
    
}
