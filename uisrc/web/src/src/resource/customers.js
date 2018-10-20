import React from 'react';
import { List, Edit, DisabledInput, EditButton, Datagrid, DateInput, NumberInput, SelectInput, TextField, Create, TextInput, LongTextInput, TabbedForm, FormTab, BooleanInput, TabbedShowLayout, Tab } from 'react-admin';

export const CustomersList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="name" />
            <TextField source="location" />
            <EditButton />
        </Datagrid>
    </List>
);

export const CustomerCreate = (props) => (
    <Create {...props}>
        <TabbedForm>
            <FormTab label="General">
                <TextInput source="name" />
                <TextInput source="location" />
                <TextInput source="area" />
                <SelectInput source="type" choices={[
                    { id: 'C', name: 'C' },
                    { id: 'N', name: 'N' },
                    { id: 'R', name: 'R' },
                ]} />

                <SelectInput source="company" choices={[
                    { id: 'S', name: 'S' },
                    { id: 'I', name: 'I' },
                ]} />
                <LongTextInput source="notes" />
            </FormTab>
            <FormTab label="Contacts">
                <h2>Location Information</h2>
                <TextInput source="contact" />
                <TextInput source="addressLine1" />
                <TextInput source="addressLine2" />
                <TextInput source="city" />
                <TextInput source="state" />
                <TextInput source="zipcode" />
                <TextInput source="phone" />
                <TextInput source="fax" />
                <h2>Billing Information</h2>
                <TextInput source="contactB" />
                <TextInput source="addressLine1B" />
                <TextInput source="addressLine2B" />
                <TextInput source="cityB" />
                <TextInput source="stateB" />
                <TextInput source="zipcodeB" />
                <TextInput source="phoneB" />
                <TextInput source="faxB" />
            </FormTab>
            <FormTab label="Accounting">
                <TextInput source="obtainedBy"/>
                <TextInput source="royaltyTo"/>
                <NumberInput source="monthlyBill" placeholder="0.0" />
                <NumberInput source="billCharge" placeholder="0.0"/>
                <NumberInput source="deposit" placeholder="0.0"/>
                <NumberInput source="salesTax" placeholder="0.0"/>
            </FormTab>
            <FormTab label="Settings">
                <DateInput source="customerSince" />
                <BooleanInput source="active" />
                <DateInput source="inactiveSince" />
                <BooleanInput source="fourWeekInvoice" />
                <BooleanInput source="calendarMonthInvoice" />
                <BooleanInput source="quarterlyInvoice" />
                <BooleanInput source="AWWPUR" />
                <BooleanInput source="AWWLine" />
            </FormTab>
        </TabbedForm>
    </Create>
);
const CustomerTitle = ({ record }) => {
    return <span>Post {record ? `"${record.name}"` : ''}</span>;
};
export const CustomerEdit = (props) => (
    <Edit title={<CustomerTitle/>} {...props}>
        <TabbedForm>

            <DisabledInput source="id" />
            <FormTab label="General">
                <TextInput source="name" />
                <TextInput source="location" />
                <TextInput source="area" />
                <SelectInput source="type" choices={[
                    { id: 'C', name: 'C' },
                    { id: 'N', name: 'N' },
                    { id: 'R', name: 'R' },
                ]} />

                <SelectInput source="company" choices={[
                    { id: 'S', name: 'S' },
                    { id: 'I', name: 'I' },
                ]} />
                <LongTextInput source="notes" />
            </FormTab>
            <FormTab label="Contacts">
                <h2>Location Information</h2>
                <TextInput source="contact" />
                <TextInput source="addressLine1" />
                <TextInput source="addressLine2" />
                <TextInput source="city" />
                <TextInput source="state" />
                <TextInput source="zipcode" />
                <TextInput source="phone" />
                <TextInput source="fax" />
                <h2>Billing Information</h2>
                <TextInput source="contactB" />
                <TextInput source="addressLine1B" />
                <TextInput source="addressLine2B" />
                <TextInput source="cityB" />
                <TextInput source="stateB" />
                <TextInput source="zipcodeB" />
                <TextInput source="phoneB" />
                <TextInput source="faxB" />
            </FormTab>
            <FormTab label="Accounting">
                <TextInput source="obtainedBy"/>
                <TextInput source="royaltyTo"/>
                <NumberInput source="monthlyBill" placeholder="0.0" />
                <NumberInput source="billCharge" placeholder="0.0"/>
                <NumberInput source="deposit" placeholder="0.0"/>
                <NumberInput source="salesTax" placeholder="0.0"/>
            </FormTab>
            <FormTab label="Settings">
                <DateInput source="customerSince" />
                <BooleanInput source="active" />
                <DateInput source="inactiveSince" />
                <BooleanInput source="fourWeekInvoice" />
                <BooleanInput source="calendarMonthInvoice" />
                <BooleanInput source="quarterlyInvoice" />
                <BooleanInput source="AWWPUR" />
                <BooleanInput source="AWWLine" />
            </FormTab>
        </TabbedForm>
    </Edit>
);